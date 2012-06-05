package pl.edu.pw.rso2012.a1.dvcs.model.operation;

import java.util.List;

import pl.edu.pw.rso2012.a1.dvcs.model.file.clock.LogicalClock;
import difflib.Patch;

/**
 * @author Oskar Leszczynski
 * 
 */

public class ChangeData
{
	private String fileName;
	private List<Patch> diffList;
	private LogicalClock vector;
	
	public String getFileName()
	{
		return fileName;
	}
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	public List<Patch> getDiffList()
	{
		return diffList;
	}
	public void setDiffList(List<Patch> diffList)
	{
		this.diffList = diffList;
	}
	public LogicalClock getVector()
	{
		return vector;
	}
	public void setVector(LogicalClock vector)
	{
		this.vector = vector;
	}
	
	public void addDiffToList(Patch patch)
	{
		diffList.add(patch);
	}
	
	public void addDiffToList(List<Patch> patch)
	{
		diffList.addAll(patch);
	}
	
	public void clearDiffList()
	{
		diffList.clear();
	}
	
}
