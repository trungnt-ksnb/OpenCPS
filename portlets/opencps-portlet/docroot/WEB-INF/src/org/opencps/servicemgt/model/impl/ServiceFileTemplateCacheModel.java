/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.opencps.servicemgt.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import org.opencps.servicemgt.model.ServiceFileTemplate;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ServiceFileTemplate in entity cache.
 *
 * @author khoavd
 * @see ServiceFileTemplate
 * @generated
 */
public class ServiceFileTemplateCacheModel implements CacheModel<ServiceFileTemplate>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{serviceinfoId=");
		sb.append(serviceinfoId);
		sb.append(", templatefileId=");
		sb.append(templatefileId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ServiceFileTemplate toEntityModel() {
		ServiceFileTemplateImpl serviceFileTemplateImpl = new ServiceFileTemplateImpl();

		serviceFileTemplateImpl.setServiceinfoId(serviceinfoId);
		serviceFileTemplateImpl.setTemplatefileId(templatefileId);

		serviceFileTemplateImpl.resetOriginalValues();

		return serviceFileTemplateImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		serviceinfoId = objectInput.readLong();
		templatefileId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(serviceinfoId);
		objectOutput.writeLong(templatefileId);
	}

	public long serviceinfoId;
	public long templatefileId;
}