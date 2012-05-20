package pl.edu.pw.rso2012.a1.dvcs.model.operation;

import java.util.HashMap;
import java.util.List;

import difflib.Patch;

import pl.edu.pw.rso2012.a1.dvcs.model.file.clock.LogicalClock;

/**
 * @author Oskar Leszczynski
 * 
 */

public class ChangeData
{
	private String fileName;
	private List<Patch> diffList;
	private LogicalClock vector;
}
