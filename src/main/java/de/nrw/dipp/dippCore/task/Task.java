package de.nrw.dipp.dippCore.task;

/**
 * <p>Title: Task.java</p>
 * <p>Description: A thread interface for handling tasks.</p>
 *
 * -----------------------------------------------------------------------------
 * Created on 29.06.2005
 * added on 06032006: isSucceeded
 *
 * -----------------------------------------------------------------------------
 *
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de
 * @version $Id: Task.java,v 1.1 2006/04/26 08:48:05 dippadm Exp $
 */
public interface Task extends Runnable{

	public Param getParam();
	public void setParam(Param aParam);
	public boolean isFinished();
	public boolean isSucceeded();
}
