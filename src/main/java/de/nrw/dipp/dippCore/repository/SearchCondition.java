package de.nrw.dipp.dippCore.repository;

/**
 * <p>Title: SearchCondition.java</p>
 * <p>Description: A wrapper class for encapsuling fedora search conditions und comparison operators.</p>
 *
 * -----------------------------------------------------------------------------
 *
 * <p><b>License and Copyright: </b>The contents of this file are subject to the
 * D-FSL License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License
 * at <a href="http://www.dipp.nrw.de/dfsl/">http://www.dipp.nrw.de/dfsl/.</a></p>
 *
 * <p>Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.</p>
 *
 * <p>Portions created for the Fedora Repository System are Copyright &copy; 2002-2005
 * by The Rector and Visitors of the University of Virginia and Cornell
 * University. All rights reserved."</p>
 *
 * -----------------------------------------------------------------------------
 *
 * @author Jochen Schirrwagen, schirrwagen@hbz-nrw.de
 * @version 
 */
public class SearchCondition {
//	contains (~), equals (=), greater than (>), 
//	less than (<), greater than or equals (>=), 
//	less than or equals (<=). 
//	The contains (~) operator may be used in combination with the ? and * wildcards to query for simple string patterns
	
	// Qa: try to skip this integers used for case blocks. 
	// They are senseless due to extra efforts needed to convert 
	// informations from controlled string values to int   
/*	public static final int	cContains 				= 0;
	public static final int	cEquals					= 1;
	public static final int	cGreaterThen			= 2;
	public static final int	cLessThen				= 3;
	public static final int	cGreaterThenOrEquals	= 4;
	public static final int	cLessThenOrEquals		= 5;
*/
	
	public static final String cContains			= "has";
	public static final String cEquals 				= "eq";
	public static final String cGreaterThen 		= "gt";
	public static final String cLessThen 			= "lt";
	public static final String cGreaterThenOrEquals	= "ge";
	public static final String cLessThenOrEquals	= "le";

	private String	_property	= "";
	private String	_value		= "";
	private String	_condition	= "";
	
	public String getCondition() {
		return _condition;
	}
	public void setCondition(String _condition) {
		this._condition = _condition;
	}
	public String getProperty() {
		return _property;
	}
	public void setProperty(String _property) {
		this._property = _property;
	}
	public String getValue() {
		return _value;
	}
	public void setValue(String _value) {
		this._value = _value;
	}
}
