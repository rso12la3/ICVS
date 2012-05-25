/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.thoughtworks.xstream.XStream;

import difflib.Patch;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.PullRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.model.changedata.ChangeData;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.Commit;
import pl.edu.pw.rso2012.a1.dvcs.model.communication.MailMessage;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.Configuration;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.RepositoryConfiguration;
import pl.edu.pw.rso2012.a1.dvcs.model.newdata.NewData;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.*;
import pl.edu.pw.rso2012.a1.dvcs.model.workingcopy.WorkingCopy;

/**
 * @author Grzegorz Sancewicz & Oskar Leszczynski
 * 
 */
public class Repository {
	private String absolutePath;
	private final WorkingCopy workingCopy;
	private final XStream xStream;

	public Repository() {
		RepositoryConfiguration repositoryConfiguration = Configuration
				.getInstance().getRepositoryConfiguration();
		this.absolutePath = repositoryConfiguration.getRepositoryAbsolutePath();
		workingCopy = new WorkingCopy(
				repositoryConfiguration.getRepositoryAddress(),
				repositoryConfiguration.getRepositoryAbsolutePath());
		this.xStream = new XStream();
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(final String absolutePath) {
		this.absolutePath = absolutePath;
	}

	public CommitOperation commit(ArrayList<String> filesToCommit) {
		Map<String, ChangeData> diffResult = workingCopy
				.diffFiles(filesToCommit);
		CommitOperation operation = new CommitOperation(diffResult);
		
		//TODO sprawdzic czy workingCopy tworzy kopie bo chyba nie tworzy kopii z working copy
		
		return operation;
	}

	public CloneRequestOperation cloneRequest() {
		return new CloneRequestOperation(this.getWorkingCopy().getAddress());
	}

	public CloneOperation prepareClone(final List<Commit> commitList) {
		return new CloneOperation(commitList, workingCopy.getAddress());
	}

	public void clone(final Controller controller, CloneOperation operation)
			throws InterruptedException {
		// Odtwarzanie repozytorium
		List<Commit> commitList = operation.getCommitList();
		List<ChangeData> changeList = prepareChangeList(commitList);
		String body;
		MailMessage message;

		// TODO Trzeba dodac do MailBox metode setCommits(List<Commit>)
		// FIXME: rozwiazanie tymczasowe do poprawki jak bedzie metoda
		// ustalone z Grzeskiem, ze zostaje jak jest
		for (Commit commit : commitList) {
			body = OperationToXML(commit.getCommitOperation());
			message = new MailMessage(workingCopy.getAddress(), "commit "
					+ commit.getRevision(), body);
			controller.getModel().getMailbox().putMessage(message);
		}

		if (!changeList.isEmpty())
			workingCopy.recoverFiles(changeList);
	}

	public PushResponseOperation push(final Controller controller,
			PushOperation operation) {
		Map<String, NewData> map = operation.getMap();
		mergeData(map);
		
		String result = "";
		PushResponseOperation opResult = new PushResponseOperation(result);
		return opResult;
	}

	public PushOperation preparePush(final Map<String, NewData> data) {
		PushOperation op = new PushOperation(data);
		return op;
	}

	public PullResponseOperation pull(final Controller controller,
			PullOperation operation) {
		
		Map<String, NewData> map = operation.getData();
		mergeData(map);
		
		String result = "";
		PullResponseOperation opResult = new PullResponseOperation(result);
		return opResult;
	}

	public PullOperation preparePull() {
		PullOperation operation = new PullOperation(
				workingCopy.getSnapshotFiles(workingCopy.getFileNames()));
		return operation;
	}

	public PushRequestOperation pushRequest() {
		List<String> files = workingCopy.getFileNames();
		Map<String, NewData> data = workingCopy.getSnapshotFiles(files);
		PushRequestOperation operation = new PushRequestOperation(data,
				workingCopy.getAddress());
		return operation;
	}

	public PullRequestOperation pullRequest()
	{
		return new PullRequestOperation(workingCopy.getAddress());
	}

	public void update(List<Commit> commitList) {
		List<ChangeData> changeList = prepareChangeList(commitList);

		if (!changeList.isEmpty())
			workingCopy.recoverFiles(changeList);
	}

	public void add(final ArrayList<String> fileList) {
		workingCopy.addFiles(fileList);
	}

	public void delete(final ArrayList<String> fileList) {
		workingCopy.deleteFiles(fileList);
	}

	public WorkingCopy getWorkingCopy() {
		return workingCopy;
	}

	protected List<ChangeData> prepareChangeList(final List<Commit> commitList) {
		List<ChangeData> changeList = new ArrayList<ChangeData>();
		if (commitList != null && !commitList.isEmpty()) {
			CommitOperation operation;
			Collections.sort(commitList);
			Map<String, ChangeData> map;
			Map<String, ChangeData> finalMap, lastMap;

			ChangeData data, finalData;

			Commit lastCommit = commitList.get(commitList.size() - 1);
			finalMap = new HashMap<String, ChangeData>();
			lastMap = lastCommit.getCommitOperation().getFilesDiffs();
			Set<String> fileSet = lastMap.keySet();

			// niebezpieczne kopiowanie, brak deep copy, wykonuje kopie
			// referencji
			// inicjalizacja nowej mapy wynikowej
			// z pusta lista diffow
			for (String filename : fileSet) {
				finalData = new ChangeData(filename);
				finalData.setVector(lastMap.get(filename).getVector());
				finalMap.put(filename, finalData);
			}

			for (int i = 0; i < commitList.size(); ++i) {
				map = commitList.get(i).getCommitOperation().getFilesDiffs();

				for (String filename : fileSet) {
					data = map.get(filename);
					finalData = map.get(filename);
					if (data != null)
						finalData.addDiffToList(data.getDifflist());
					else
						finalData.clearDiffList();
				}
			}

			changeList.addAll(finalMap.values());

			return changeList;
		} else {
			// TODO zastanowic sie czy dla null nie powinno rzucac wyjatkiem
			return changeList;
		}
	}

	public String OperationToXML(AbstractOperation operation) {
		return xStream.toXML(operation);
	}

	public AbstractOperation OperationFromXML(String xml) {
		return (AbstractOperation) xStream.fromXML(xml);
	}
	
	//zwraca komunikat ktory bedzie wyswietlony na gui
	//tzn zawierajacy liste plikow z konfliktami
	protected String mergeData(Map<String, NewData> map)
	{
		//TODO tutaj logika scalenia
		
		return null;
	}
}
