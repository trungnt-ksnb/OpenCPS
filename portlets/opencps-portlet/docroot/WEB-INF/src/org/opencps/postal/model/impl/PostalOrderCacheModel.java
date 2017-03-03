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

package org.opencps.postal.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.opencps.postal.model.PostalOrder;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PostalOrder in entity cache.
 *
 * @author nhanhlt
 * @see PostalOrder
 * @generated
 */
public class PostalOrderCacheModel implements CacheModel<PostalOrder>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{postalOrderId=");
		sb.append(postalOrderId);
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
		sb.append(", dossierId=");
		sb.append(dossierId);
		sb.append(", postalOrderContent=");
		sb.append(postalOrderContent);
		sb.append(", postalOrderStatus=");
		sb.append(postalOrderStatus);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PostalOrder toEntityModel() {
		PostalOrderImpl postalOrderImpl = new PostalOrderImpl();

		postalOrderImpl.setPostalOrderId(postalOrderId);
		postalOrderImpl.setCompanyId(companyId);
		postalOrderImpl.setGroupId(groupId);
		postalOrderImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			postalOrderImpl.setCreateDate(null);
		}
		else {
			postalOrderImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			postalOrderImpl.setModifiedDate(null);
		}
		else {
			postalOrderImpl.setModifiedDate(new Date(modifiedDate));
		}

		postalOrderImpl.setDossierId(dossierId);

		if (postalOrderContent == null) {
			postalOrderImpl.setPostalOrderContent(StringPool.BLANK);
		}
		else {
			postalOrderImpl.setPostalOrderContent(postalOrderContent);
		}

		postalOrderImpl.setPostalOrderStatus(postalOrderStatus);

		postalOrderImpl.resetOriginalValues();

		return postalOrderImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		postalOrderId = objectInput.readLong();
		companyId = objectInput.readLong();
		groupId = objectInput.readLong();
		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		dossierId = objectInput.readLong();
		postalOrderContent = objectInput.readUTF();
		postalOrderStatus = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(postalOrderId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(dossierId);

		if (postalOrderContent == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(postalOrderContent);
		}

		objectOutput.writeInt(postalOrderStatus);
	}

	public long postalOrderId;
	public long companyId;
	public long groupId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long dossierId;
	public String postalOrderContent;
	public int postalOrderStatus;
}