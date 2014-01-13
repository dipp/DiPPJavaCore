/*
 * Created on 14.09.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.nrw.dipp.dippCore.task;

import java.util.Hashtable;

/**
 * @author SCHIRRWAGEN
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ConvertProperties {

		private boolean mOrigNumbering 			= false;
		private String	mDefaultFontSize		= "12";
		private boolean mIncludeImages			= true;
		private boolean mDocbaseImageNaming		= true;
		private boolean mInlineReferencedImages	= true;
		private String	mDefaultPixmapResolution	= "*";
		private String 	mImageRenderingResolution	= "96";
		private String 	mFormatacronymDestFormat	= "unchanged";
		private boolean	mRespectParOutlineLevel		= false;
		private boolean mGroupEmptyHeadings			= false;
		private boolean mHoistCommonInlines			= false;
		private boolean mRemoveEmptyInlines			= false;
		private Hashtable	mConvertTable			= new Hashtable();
		
		public Hashtable getConvertProperties(){
			return mConvertTable;
		}
		public void setDefaultFontSize(String defaultFontSize) {
			mConvertTable.put("DefaultFontSize", defaultFontSize);
		}
		public void setDefaultPixmapResolution(String defaultPixmapResolution) {
			mConvertTable.put("DefaultPixmapResolution", defaultPixmapResolution);
		}
		public void setDocbaseImageNaming(boolean docbaseImageNaming) {
			mConvertTable.put("DocbaseImageNaming", new Boolean(docbaseImageNaming));
		}
		public void setFormatacronymDestFormat(String formatacronymDestFormat) {
			mConvertTable.put("FormatacronymDestFormat", formatacronymDestFormat);
		}
		public void setGroupEmptyHeadings(boolean groupEmptyHeadings) {
			mConvertTable.put("GroupEmptyHeadings", new Boolean(groupEmptyHeadings));
		}
		public void setHoistCommonInlines(boolean hoistCommonInlines) {
			mConvertTable.put("HoistCommonInlines", new Boolean(hoistCommonInlines));
		}
		public void setImageRenderingResolution(String imageRenderingResolution) {
			mConvertTable.put("ImageRenderingResolution", imageRenderingResolution);
		}
		public void setIncludeImages(boolean includeImages) {
			mConvertTable.put("IncludeImages", new Boolean(includeImages));
		}
		public void setInlineReferencedImages(boolean inlineReferencedImages) {
			mConvertTable.put("InlineReferencedImages", new Boolean(inlineReferencedImages));
		}
		public void setOrigNumbering(boolean origNumbering) {
			mConvertTable.put("OrigNumbering", new Boolean(origNumbering));
		}
		public void setRespectParOutlineLevel(boolean respectParOutlineLevel) {
			mConvertTable.put("RespectParOutlineLevel", new Boolean(respectParOutlineLevel));
		}
		public void setRemoveEmptyInlines(boolean removeEmptyInlines) {
			mConvertTable.put("RemoveEmptyInlines", new Boolean(removeEmptyInlines));
		}
}
