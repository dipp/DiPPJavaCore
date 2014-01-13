package de.nrw.dipp.dippCore.repository.contentmodel;

import java.util.Properties;

import de.nrw.dipp.dippCore.util.Constant;

public class ModelFactoryImpl implements ModelFactory {

	private static final Properties modelProps = new Properties();
	
	public ModelFactoryImpl(){
		modelProps.setProperty(Constant.cContentModelArticle,"de.nrw.dipp.dippCore.repository.contentmodel.DippArticle");
		modelProps.setProperty(Constant.cContentModelContainer,"de.nrw.dipp.dippCore.repository.contentmodel.DippContainer");
		modelProps.setProperty(Constant.cContentModelJournal,"de.nrw.dipp.dippCore.repository.contentmodel.DippJournal");
		modelProps.setProperty(Constant.cContentModelData,"de.nrw.dipp.dippCore.repository.contentmodel.DippContent");
		modelProps.setProperty(Constant.cContentModelOaiSet,"de.nrw.dipp.dippCore.repository.contentmodel.OaiSet");
	}
	
	public MetaModel createModel(String aModel) {
		Object obj = null;
		try{
			obj = (MetaModel)Class.forName(modelProps.getProperty(aModel)).newInstance();
		}catch(Exception exc){
			exc.printStackTrace();
		}
		return (MetaModel)obj;
	}

}
