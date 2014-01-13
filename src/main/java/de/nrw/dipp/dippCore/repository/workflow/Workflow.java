package de.nrw.dipp.dippCore.repository.workflow;

/**
 * 
 * @author SCHIRRWAGEN
 *
 */
public interface Workflow {

	State getInitialState();
	Transition[] getLeavingTransitions(State aState);
}
