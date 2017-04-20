package org.opencps.lucenequery.portlet;

import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class LuceneQueryPortlet
 */
public class LuceneQueryPortlet extends MVCPortlet {
	public void test() {
		BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create(searchContext)
	}

}
