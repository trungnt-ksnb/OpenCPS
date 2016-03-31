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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.opencps.servicemgt.model.TemplateFile;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TemplateFile in entity cache.
 *
 * @author khoavd
 * @see TemplateFile
 * @generated
 */
public class TemplateFileCacheModel implements CacheModel<TemplateFile>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{templatefileId=");
		sb.append(templatefileId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", fileName=");
		sb.append(fileName);
		sb.append(", fileNo=");
		sb.append(fileNo);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TemplateFile toEntityModel() {
		TemplateFileImpl templateFileImpl = new TemplateFileImpl();

		templateFileImpl.setTemplatefileId(templatefileId);
		templateFileImpl.setCompanyId(companyId);
		templateFileImpl.setGroupId(groupId);
		templateFileImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			templateFileImpl.setCreateDate(null);
		}
		else {
			templateFileImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			templateFileImpl.setModifiedDate(null);
		}
		else {
			templateFileImpl.setModifiedDate(new Date(modifiedDate));
		}

		templateFileImpl.setFileName(fileName);

		if (fileNo == null) {
			templateFileImpl.setFileNo(StringPool.BLANK);
		}
		else {
			templateFileImpl.setFileNo(fileNo);
		}

		if (fileEntryId == null) {
			templateFileImpl.setFileEntryId(StringPool.BLANK);
		}
		else {
			templateFileImpl.setFileEntryId(fileEntryId);
		}

		templateFileImpl.resetOriginalValues();

		return templateFileImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		templatefileId = objectInput.readLong();
		companyId = objectInput.readLong();
		groupId = objectInput.readLong();
		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		fileName = objectInput.readLong();
		fileNo = objectInput.readUTF();
		fileEntryId = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(templatefileId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(fileName);

		if (fileNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fileNo);
		}

		if (fileEntryId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fileEntryId);
		}
	}

	public long templatefileId;
	public long companyId;
	public long groupId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long fileName;
	public String fileNo;
	public String fileEntryId;
}