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

package org.opencps.paymentmgt.service.persistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException;
import org.opencps.paymentmgt.model.PaymentGateConfigCode;
import org.opencps.paymentmgt.model.impl.PaymentGateConfigCodeImpl;
import org.opencps.paymentmgt.model.impl.PaymentGateConfigCodeModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the payment gate config code service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see PaymentGateConfigCodePersistence
 * @see PaymentGateConfigCodeUtil
 * @generated
 */
public class PaymentGateConfigCodePersistenceImpl extends BasePersistenceImpl<PaymentGateConfigCode>
	implements PaymentGateConfigCodePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PaymentGateConfigCodeUtil} to access the payment gate config code persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PaymentGateConfigCodeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeModelImpl.FINDER_CACHE_ENABLED,
			PaymentGateConfigCodeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeModelImpl.FINDER_CACHE_ENABLED,
			PaymentGateConfigCodeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_E_P = new FinderPath(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeModelImpl.FINDER_CACHE_ENABLED,
			PaymentGateConfigCodeImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByE_P",
			new String[] { Integer.class.getName(), String.class.getName() },
			PaymentGateConfigCodeModelImpl.ERRORCODE_COLUMN_BITMASK |
			PaymentGateConfigCodeModelImpl.PAYMENTGATENAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_E_P = new FinderPath(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByE_P",
			new String[] { Integer.class.getName(), String.class.getName() });

	/**
	 * Returns the payment gate config code where errorCode = &#63; and paymentGateName = &#63; or throws a {@link org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException} if it could not be found.
	 *
	 * @param errorCode the error code
	 * @param paymentGateName the payment gate name
	 * @return the matching payment gate config code
	 * @throws org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode findByE_P(int errorCode, String paymentGateName)
		throws NoSuchPaymentGateConfigCodeException, SystemException {
		PaymentGateConfigCode paymentGateConfigCode = fetchByE_P(errorCode,
				paymentGateName);

		if (paymentGateConfigCode == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("errorCode=");
			msg.append(errorCode);

			msg.append(", paymentGateName=");
			msg.append(paymentGateName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchPaymentGateConfigCodeException(msg.toString());
		}

		return paymentGateConfigCode;
	}

	/**
	 * Returns the payment gate config code where errorCode = &#63; and paymentGateName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param errorCode the error code
	 * @param paymentGateName the payment gate name
	 * @return the matching payment gate config code, or <code>null</code> if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode fetchByE_P(int errorCode,
		String paymentGateName) throws SystemException {
		return fetchByE_P(errorCode, paymentGateName, true);
	}

	/**
	 * Returns the payment gate config code where errorCode = &#63; and paymentGateName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param errorCode the error code
	 * @param paymentGateName the payment gate name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching payment gate config code, or <code>null</code> if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode fetchByE_P(int errorCode,
		String paymentGateName, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { errorCode, paymentGateName };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_E_P,
					finderArgs, this);
		}

		if (result instanceof PaymentGateConfigCode) {
			PaymentGateConfigCode paymentGateConfigCode = (PaymentGateConfigCode)result;

			if ((errorCode != paymentGateConfigCode.getErrorCode()) ||
					!Validator.equals(paymentGateName,
						paymentGateConfigCode.getPaymentGateName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PAYMENTGATECONFIGCODE_WHERE);

			query.append(_FINDER_COLUMN_E_P_ERRORCODE_2);

			boolean bindPaymentGateName = false;

			if (paymentGateName == null) {
				query.append(_FINDER_COLUMN_E_P_PAYMENTGATENAME_1);
			}
			else if (paymentGateName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_E_P_PAYMENTGATENAME_3);
			}
			else {
				bindPaymentGateName = true;

				query.append(_FINDER_COLUMN_E_P_PAYMENTGATENAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(errorCode);

				if (bindPaymentGateName) {
					qPos.add(paymentGateName);
				}

				List<PaymentGateConfigCode> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_E_P,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"PaymentGateConfigCodePersistenceImpl.fetchByE_P(int, String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					PaymentGateConfigCode paymentGateConfigCode = list.get(0);

					result = paymentGateConfigCode;

					cacheResult(paymentGateConfigCode);

					if ((paymentGateConfigCode.getErrorCode() != errorCode) ||
							(paymentGateConfigCode.getPaymentGateName() == null) ||
							!paymentGateConfigCode.getPaymentGateName()
													  .equals(paymentGateName)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_E_P,
							finderArgs, paymentGateConfigCode);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_E_P,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (PaymentGateConfigCode)result;
		}
	}

	/**
	 * Removes the payment gate config code where errorCode = &#63; and paymentGateName = &#63; from the database.
	 *
	 * @param errorCode the error code
	 * @param paymentGateName the payment gate name
	 * @return the payment gate config code that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode removeByE_P(int errorCode,
		String paymentGateName)
		throws NoSuchPaymentGateConfigCodeException, SystemException {
		PaymentGateConfigCode paymentGateConfigCode = findByE_P(errorCode,
				paymentGateName);

		return remove(paymentGateConfigCode);
	}

	/**
	 * Returns the number of payment gate config codes where errorCode = &#63; and paymentGateName = &#63;.
	 *
	 * @param errorCode the error code
	 * @param paymentGateName the payment gate name
	 * @return the number of matching payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByE_P(int errorCode, String paymentGateName)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_E_P;

		Object[] finderArgs = new Object[] { errorCode, paymentGateName };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PAYMENTGATECONFIGCODE_WHERE);

			query.append(_FINDER_COLUMN_E_P_ERRORCODE_2);

			boolean bindPaymentGateName = false;

			if (paymentGateName == null) {
				query.append(_FINDER_COLUMN_E_P_PAYMENTGATENAME_1);
			}
			else if (paymentGateName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_E_P_PAYMENTGATENAME_3);
			}
			else {
				bindPaymentGateName = true;

				query.append(_FINDER_COLUMN_E_P_PAYMENTGATENAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(errorCode);

				if (bindPaymentGateName) {
					qPos.add(paymentGateName);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_E_P_ERRORCODE_2 = "paymentGateConfigCode.errorCode = ? AND ";
	private static final String _FINDER_COLUMN_E_P_PAYMENTGATENAME_1 = "paymentGateConfigCode.paymentGateName IS NULL";
	private static final String _FINDER_COLUMN_E_P_PAYMENTGATENAME_2 = "paymentGateConfigCode.paymentGateName = ?";
	private static final String _FINDER_COLUMN_E_P_PAYMENTGATENAME_3 = "(paymentGateConfigCode.paymentGateName IS NULL OR paymentGateConfigCode.paymentGateName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PAYMENTGATEID =
		new FinderPath(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeModelImpl.FINDER_CACHE_ENABLED,
			PaymentGateConfigCodeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPaymentGateId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTGATEID =
		new FinderPath(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeModelImpl.FINDER_CACHE_ENABLED,
			PaymentGateConfigCodeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPaymentGateId",
			new String[] { Long.class.getName() },
			PaymentGateConfigCodeModelImpl.PAYMENTGATEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PAYMENTGATEID = new FinderPath(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPaymentGateId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the payment gate config codes where paymentGateId = &#63;.
	 *
	 * @param paymentGateId the payment gate ID
	 * @return the matching payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PaymentGateConfigCode> findByPaymentGateId(long paymentGateId)
		throws SystemException {
		return findByPaymentGateId(paymentGateId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment gate config codes where paymentGateId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.paymentmgt.model.impl.PaymentGateConfigCodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param paymentGateId the payment gate ID
	 * @param start the lower bound of the range of payment gate config codes
	 * @param end the upper bound of the range of payment gate config codes (not inclusive)
	 * @return the range of matching payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PaymentGateConfigCode> findByPaymentGateId(long paymentGateId,
		int start, int end) throws SystemException {
		return findByPaymentGateId(paymentGateId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment gate config codes where paymentGateId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.paymentmgt.model.impl.PaymentGateConfigCodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param paymentGateId the payment gate ID
	 * @param start the lower bound of the range of payment gate config codes
	 * @param end the upper bound of the range of payment gate config codes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PaymentGateConfigCode> findByPaymentGateId(long paymentGateId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTGATEID;
			finderArgs = new Object[] { paymentGateId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PAYMENTGATEID;
			finderArgs = new Object[] {
					paymentGateId,
					
					start, end, orderByComparator
				};
		}

		List<PaymentGateConfigCode> list = (List<PaymentGateConfigCode>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PaymentGateConfigCode paymentGateConfigCode : list) {
				if ((paymentGateId != paymentGateConfigCode.getPaymentGateId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_PAYMENTGATECONFIGCODE_WHERE);

			query.append(_FINDER_COLUMN_PAYMENTGATEID_PAYMENTGATEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PaymentGateConfigCodeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(paymentGateId);

				if (!pagination) {
					list = (List<PaymentGateConfigCode>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<PaymentGateConfigCode>(list);
				}
				else {
					list = (List<PaymentGateConfigCode>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first payment gate config code in the ordered set where paymentGateId = &#63;.
	 *
	 * @param paymentGateId the payment gate ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment gate config code
	 * @throws org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode findByPaymentGateId_First(long paymentGateId,
		OrderByComparator orderByComparator)
		throws NoSuchPaymentGateConfigCodeException, SystemException {
		PaymentGateConfigCode paymentGateConfigCode = fetchByPaymentGateId_First(paymentGateId,
				orderByComparator);

		if (paymentGateConfigCode != null) {
			return paymentGateConfigCode;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("paymentGateId=");
		msg.append(paymentGateId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPaymentGateConfigCodeException(msg.toString());
	}

	/**
	 * Returns the first payment gate config code in the ordered set where paymentGateId = &#63;.
	 *
	 * @param paymentGateId the payment gate ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment gate config code, or <code>null</code> if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode fetchByPaymentGateId_First(
		long paymentGateId, OrderByComparator orderByComparator)
		throws SystemException {
		List<PaymentGateConfigCode> list = findByPaymentGateId(paymentGateId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment gate config code in the ordered set where paymentGateId = &#63;.
	 *
	 * @param paymentGateId the payment gate ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment gate config code
	 * @throws org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode findByPaymentGateId_Last(long paymentGateId,
		OrderByComparator orderByComparator)
		throws NoSuchPaymentGateConfigCodeException, SystemException {
		PaymentGateConfigCode paymentGateConfigCode = fetchByPaymentGateId_Last(paymentGateId,
				orderByComparator);

		if (paymentGateConfigCode != null) {
			return paymentGateConfigCode;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("paymentGateId=");
		msg.append(paymentGateId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPaymentGateConfigCodeException(msg.toString());
	}

	/**
	 * Returns the last payment gate config code in the ordered set where paymentGateId = &#63;.
	 *
	 * @param paymentGateId the payment gate ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment gate config code, or <code>null</code> if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode fetchByPaymentGateId_Last(long paymentGateId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPaymentGateId(paymentGateId);

		if (count == 0) {
			return null;
		}

		List<PaymentGateConfigCode> list = findByPaymentGateId(paymentGateId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payment gate config codes before and after the current payment gate config code in the ordered set where paymentGateId = &#63;.
	 *
	 * @param payGateErrorId the primary key of the current payment gate config code
	 * @param paymentGateId the payment gate ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment gate config code
	 * @throws org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException if a payment gate config code with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode[] findByPaymentGateId_PrevAndNext(
		long payGateErrorId, long paymentGateId,
		OrderByComparator orderByComparator)
		throws NoSuchPaymentGateConfigCodeException, SystemException {
		PaymentGateConfigCode paymentGateConfigCode = findByPrimaryKey(payGateErrorId);

		Session session = null;

		try {
			session = openSession();

			PaymentGateConfigCode[] array = new PaymentGateConfigCodeImpl[3];

			array[0] = getByPaymentGateId_PrevAndNext(session,
					paymentGateConfigCode, paymentGateId, orderByComparator,
					true);

			array[1] = paymentGateConfigCode;

			array[2] = getByPaymentGateId_PrevAndNext(session,
					paymentGateConfigCode, paymentGateId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PaymentGateConfigCode getByPaymentGateId_PrevAndNext(
		Session session, PaymentGateConfigCode paymentGateConfigCode,
		long paymentGateId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PAYMENTGATECONFIGCODE_WHERE);

		query.append(_FINDER_COLUMN_PAYMENTGATEID_PAYMENTGATEID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(PaymentGateConfigCodeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(paymentGateId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(paymentGateConfigCode);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PaymentGateConfigCode> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the payment gate config codes where paymentGateId = &#63; from the database.
	 *
	 * @param paymentGateId the payment gate ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByPaymentGateId(long paymentGateId)
		throws SystemException {
		for (PaymentGateConfigCode paymentGateConfigCode : findByPaymentGateId(
				paymentGateId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(paymentGateConfigCode);
		}
	}

	/**
	 * Returns the number of payment gate config codes where paymentGateId = &#63;.
	 *
	 * @param paymentGateId the payment gate ID
	 * @return the number of matching payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByPaymentGateId(long paymentGateId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PAYMENTGATEID;

		Object[] finderArgs = new Object[] { paymentGateId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PAYMENTGATECONFIGCODE_WHERE);

			query.append(_FINDER_COLUMN_PAYMENTGATEID_PAYMENTGATEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(paymentGateId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_PAYMENTGATEID_PAYMENTGATEID_2 = "paymentGateConfigCode.paymentGateId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PAYMENTGATENAME =
		new FinderPath(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeModelImpl.FINDER_CACHE_ENABLED,
			PaymentGateConfigCodeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPaymentGateName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTGATENAME =
		new FinderPath(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeModelImpl.FINDER_CACHE_ENABLED,
			PaymentGateConfigCodeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPaymentGateName",
			new String[] { String.class.getName() },
			PaymentGateConfigCodeModelImpl.PAYMENTGATENAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PAYMENTGATENAME = new FinderPath(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPaymentGateName", new String[] { String.class.getName() });

	/**
	 * Returns all the payment gate config codes where paymentGateName = &#63;.
	 *
	 * @param paymentGateName the payment gate name
	 * @return the matching payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PaymentGateConfigCode> findByPaymentGateName(
		String paymentGateName) throws SystemException {
		return findByPaymentGateName(paymentGateName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment gate config codes where paymentGateName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.paymentmgt.model.impl.PaymentGateConfigCodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param paymentGateName the payment gate name
	 * @param start the lower bound of the range of payment gate config codes
	 * @param end the upper bound of the range of payment gate config codes (not inclusive)
	 * @return the range of matching payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PaymentGateConfigCode> findByPaymentGateName(
		String paymentGateName, int start, int end) throws SystemException {
		return findByPaymentGateName(paymentGateName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment gate config codes where paymentGateName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.paymentmgt.model.impl.PaymentGateConfigCodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param paymentGateName the payment gate name
	 * @param start the lower bound of the range of payment gate config codes
	 * @param end the upper bound of the range of payment gate config codes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PaymentGateConfigCode> findByPaymentGateName(
		String paymentGateName, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTGATENAME;
			finderArgs = new Object[] { paymentGateName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PAYMENTGATENAME;
			finderArgs = new Object[] {
					paymentGateName,
					
					start, end, orderByComparator
				};
		}

		List<PaymentGateConfigCode> list = (List<PaymentGateConfigCode>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PaymentGateConfigCode paymentGateConfigCode : list) {
				if (!Validator.equals(paymentGateName,
							paymentGateConfigCode.getPaymentGateName())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_PAYMENTGATECONFIGCODE_WHERE);

			boolean bindPaymentGateName = false;

			if (paymentGateName == null) {
				query.append(_FINDER_COLUMN_PAYMENTGATENAME_PAYMENTGATENAME_1);
			}
			else if (paymentGateName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PAYMENTGATENAME_PAYMENTGATENAME_3);
			}
			else {
				bindPaymentGateName = true;

				query.append(_FINDER_COLUMN_PAYMENTGATENAME_PAYMENTGATENAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PaymentGateConfigCodeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPaymentGateName) {
					qPos.add(paymentGateName);
				}

				if (!pagination) {
					list = (List<PaymentGateConfigCode>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<PaymentGateConfigCode>(list);
				}
				else {
					list = (List<PaymentGateConfigCode>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first payment gate config code in the ordered set where paymentGateName = &#63;.
	 *
	 * @param paymentGateName the payment gate name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment gate config code
	 * @throws org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode findByPaymentGateName_First(
		String paymentGateName, OrderByComparator orderByComparator)
		throws NoSuchPaymentGateConfigCodeException, SystemException {
		PaymentGateConfigCode paymentGateConfigCode = fetchByPaymentGateName_First(paymentGateName,
				orderByComparator);

		if (paymentGateConfigCode != null) {
			return paymentGateConfigCode;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("paymentGateName=");
		msg.append(paymentGateName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPaymentGateConfigCodeException(msg.toString());
	}

	/**
	 * Returns the first payment gate config code in the ordered set where paymentGateName = &#63;.
	 *
	 * @param paymentGateName the payment gate name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment gate config code, or <code>null</code> if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode fetchByPaymentGateName_First(
		String paymentGateName, OrderByComparator orderByComparator)
		throws SystemException {
		List<PaymentGateConfigCode> list = findByPaymentGateName(paymentGateName,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment gate config code in the ordered set where paymentGateName = &#63;.
	 *
	 * @param paymentGateName the payment gate name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment gate config code
	 * @throws org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode findByPaymentGateName_Last(
		String paymentGateName, OrderByComparator orderByComparator)
		throws NoSuchPaymentGateConfigCodeException, SystemException {
		PaymentGateConfigCode paymentGateConfigCode = fetchByPaymentGateName_Last(paymentGateName,
				orderByComparator);

		if (paymentGateConfigCode != null) {
			return paymentGateConfigCode;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("paymentGateName=");
		msg.append(paymentGateName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPaymentGateConfigCodeException(msg.toString());
	}

	/**
	 * Returns the last payment gate config code in the ordered set where paymentGateName = &#63;.
	 *
	 * @param paymentGateName the payment gate name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment gate config code, or <code>null</code> if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode fetchByPaymentGateName_Last(
		String paymentGateName, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByPaymentGateName(paymentGateName);

		if (count == 0) {
			return null;
		}

		List<PaymentGateConfigCode> list = findByPaymentGateName(paymentGateName,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payment gate config codes before and after the current payment gate config code in the ordered set where paymentGateName = &#63;.
	 *
	 * @param payGateErrorId the primary key of the current payment gate config code
	 * @param paymentGateName the payment gate name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment gate config code
	 * @throws org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException if a payment gate config code with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode[] findByPaymentGateName_PrevAndNext(
		long payGateErrorId, String paymentGateName,
		OrderByComparator orderByComparator)
		throws NoSuchPaymentGateConfigCodeException, SystemException {
		PaymentGateConfigCode paymentGateConfigCode = findByPrimaryKey(payGateErrorId);

		Session session = null;

		try {
			session = openSession();

			PaymentGateConfigCode[] array = new PaymentGateConfigCodeImpl[3];

			array[0] = getByPaymentGateName_PrevAndNext(session,
					paymentGateConfigCode, paymentGateName, orderByComparator,
					true);

			array[1] = paymentGateConfigCode;

			array[2] = getByPaymentGateName_PrevAndNext(session,
					paymentGateConfigCode, paymentGateName, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PaymentGateConfigCode getByPaymentGateName_PrevAndNext(
		Session session, PaymentGateConfigCode paymentGateConfigCode,
		String paymentGateName, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PAYMENTGATECONFIGCODE_WHERE);

		boolean bindPaymentGateName = false;

		if (paymentGateName == null) {
			query.append(_FINDER_COLUMN_PAYMENTGATENAME_PAYMENTGATENAME_1);
		}
		else if (paymentGateName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PAYMENTGATENAME_PAYMENTGATENAME_3);
		}
		else {
			bindPaymentGateName = true;

			query.append(_FINDER_COLUMN_PAYMENTGATENAME_PAYMENTGATENAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(PaymentGateConfigCodeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPaymentGateName) {
			qPos.add(paymentGateName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(paymentGateConfigCode);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PaymentGateConfigCode> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the payment gate config codes where paymentGateName = &#63; from the database.
	 *
	 * @param paymentGateName the payment gate name
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByPaymentGateName(String paymentGateName)
		throws SystemException {
		for (PaymentGateConfigCode paymentGateConfigCode : findByPaymentGateName(
				paymentGateName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(paymentGateConfigCode);
		}
	}

	/**
	 * Returns the number of payment gate config codes where paymentGateName = &#63;.
	 *
	 * @param paymentGateName the payment gate name
	 * @return the number of matching payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByPaymentGateName(String paymentGateName)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PAYMENTGATENAME;

		Object[] finderArgs = new Object[] { paymentGateName };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PAYMENTGATECONFIGCODE_WHERE);

			boolean bindPaymentGateName = false;

			if (paymentGateName == null) {
				query.append(_FINDER_COLUMN_PAYMENTGATENAME_PAYMENTGATENAME_1);
			}
			else if (paymentGateName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PAYMENTGATENAME_PAYMENTGATENAME_3);
			}
			else {
				bindPaymentGateName = true;

				query.append(_FINDER_COLUMN_PAYMENTGATENAME_PAYMENTGATENAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPaymentGateName) {
					qPos.add(paymentGateName);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_PAYMENTGATENAME_PAYMENTGATENAME_1 =
		"paymentGateConfigCode.paymentGateName IS NULL";
	private static final String _FINDER_COLUMN_PAYMENTGATENAME_PAYMENTGATENAME_2 =
		"paymentGateConfigCode.paymentGateName = ?";
	private static final String _FINDER_COLUMN_PAYMENTGATENAME_PAYMENTGATENAME_3 =
		"(paymentGateConfigCode.paymentGateName IS NULL OR paymentGateConfigCode.paymentGateName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_REQUIRECHECK =
		new FinderPath(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeModelImpl.FINDER_CACHE_ENABLED,
			PaymentGateConfigCodeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByrequireCheck",
			new String[] {
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REQUIRECHECK =
		new FinderPath(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeModelImpl.FINDER_CACHE_ENABLED,
			PaymentGateConfigCodeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByrequireCheck",
			new String[] { Boolean.class.getName() },
			PaymentGateConfigCodeModelImpl.REQUIRECHECKSTATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_REQUIRECHECK = new FinderPath(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByrequireCheck",
			new String[] { Boolean.class.getName() });

	/**
	 * Returns all the payment gate config codes where requireCheckStatus = &#63;.
	 *
	 * @param requireCheckStatus the require check status
	 * @return the matching payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PaymentGateConfigCode> findByrequireCheck(
		boolean requireCheckStatus) throws SystemException {
		return findByrequireCheck(requireCheckStatus, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment gate config codes where requireCheckStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.paymentmgt.model.impl.PaymentGateConfigCodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param requireCheckStatus the require check status
	 * @param start the lower bound of the range of payment gate config codes
	 * @param end the upper bound of the range of payment gate config codes (not inclusive)
	 * @return the range of matching payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PaymentGateConfigCode> findByrequireCheck(
		boolean requireCheckStatus, int start, int end)
		throws SystemException {
		return findByrequireCheck(requireCheckStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment gate config codes where requireCheckStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.paymentmgt.model.impl.PaymentGateConfigCodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param requireCheckStatus the require check status
	 * @param start the lower bound of the range of payment gate config codes
	 * @param end the upper bound of the range of payment gate config codes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PaymentGateConfigCode> findByrequireCheck(
		boolean requireCheckStatus, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REQUIRECHECK;
			finderArgs = new Object[] { requireCheckStatus };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_REQUIRECHECK;
			finderArgs = new Object[] {
					requireCheckStatus,
					
					start, end, orderByComparator
				};
		}

		List<PaymentGateConfigCode> list = (List<PaymentGateConfigCode>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PaymentGateConfigCode paymentGateConfigCode : list) {
				if ((requireCheckStatus != paymentGateConfigCode.getRequireCheckStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_PAYMENTGATECONFIGCODE_WHERE);

			query.append(_FINDER_COLUMN_REQUIRECHECK_REQUIRECHECKSTATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PaymentGateConfigCodeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(requireCheckStatus);

				if (!pagination) {
					list = (List<PaymentGateConfigCode>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<PaymentGateConfigCode>(list);
				}
				else {
					list = (List<PaymentGateConfigCode>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first payment gate config code in the ordered set where requireCheckStatus = &#63;.
	 *
	 * @param requireCheckStatus the require check status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment gate config code
	 * @throws org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode findByrequireCheck_First(
		boolean requireCheckStatus, OrderByComparator orderByComparator)
		throws NoSuchPaymentGateConfigCodeException, SystemException {
		PaymentGateConfigCode paymentGateConfigCode = fetchByrequireCheck_First(requireCheckStatus,
				orderByComparator);

		if (paymentGateConfigCode != null) {
			return paymentGateConfigCode;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requireCheckStatus=");
		msg.append(requireCheckStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPaymentGateConfigCodeException(msg.toString());
	}

	/**
	 * Returns the first payment gate config code in the ordered set where requireCheckStatus = &#63;.
	 *
	 * @param requireCheckStatus the require check status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment gate config code, or <code>null</code> if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode fetchByrequireCheck_First(
		boolean requireCheckStatus, OrderByComparator orderByComparator)
		throws SystemException {
		List<PaymentGateConfigCode> list = findByrequireCheck(requireCheckStatus,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment gate config code in the ordered set where requireCheckStatus = &#63;.
	 *
	 * @param requireCheckStatus the require check status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment gate config code
	 * @throws org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode findByrequireCheck_Last(
		boolean requireCheckStatus, OrderByComparator orderByComparator)
		throws NoSuchPaymentGateConfigCodeException, SystemException {
		PaymentGateConfigCode paymentGateConfigCode = fetchByrequireCheck_Last(requireCheckStatus,
				orderByComparator);

		if (paymentGateConfigCode != null) {
			return paymentGateConfigCode;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requireCheckStatus=");
		msg.append(requireCheckStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPaymentGateConfigCodeException(msg.toString());
	}

	/**
	 * Returns the last payment gate config code in the ordered set where requireCheckStatus = &#63;.
	 *
	 * @param requireCheckStatus the require check status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment gate config code, or <code>null</code> if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode fetchByrequireCheck_Last(
		boolean requireCheckStatus, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByrequireCheck(requireCheckStatus);

		if (count == 0) {
			return null;
		}

		List<PaymentGateConfigCode> list = findByrequireCheck(requireCheckStatus,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payment gate config codes before and after the current payment gate config code in the ordered set where requireCheckStatus = &#63;.
	 *
	 * @param payGateErrorId the primary key of the current payment gate config code
	 * @param requireCheckStatus the require check status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment gate config code
	 * @throws org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException if a payment gate config code with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode[] findByrequireCheck_PrevAndNext(
		long payGateErrorId, boolean requireCheckStatus,
		OrderByComparator orderByComparator)
		throws NoSuchPaymentGateConfigCodeException, SystemException {
		PaymentGateConfigCode paymentGateConfigCode = findByPrimaryKey(payGateErrorId);

		Session session = null;

		try {
			session = openSession();

			PaymentGateConfigCode[] array = new PaymentGateConfigCodeImpl[3];

			array[0] = getByrequireCheck_PrevAndNext(session,
					paymentGateConfigCode, requireCheckStatus,
					orderByComparator, true);

			array[1] = paymentGateConfigCode;

			array[2] = getByrequireCheck_PrevAndNext(session,
					paymentGateConfigCode, requireCheckStatus,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PaymentGateConfigCode getByrequireCheck_PrevAndNext(
		Session session, PaymentGateConfigCode paymentGateConfigCode,
		boolean requireCheckStatus, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PAYMENTGATECONFIGCODE_WHERE);

		query.append(_FINDER_COLUMN_REQUIRECHECK_REQUIRECHECKSTATUS_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(PaymentGateConfigCodeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(requireCheckStatus);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(paymentGateConfigCode);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PaymentGateConfigCode> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the payment gate config codes where requireCheckStatus = &#63; from the database.
	 *
	 * @param requireCheckStatus the require check status
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByrequireCheck(boolean requireCheckStatus)
		throws SystemException {
		for (PaymentGateConfigCode paymentGateConfigCode : findByrequireCheck(
				requireCheckStatus, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(paymentGateConfigCode);
		}
	}

	/**
	 * Returns the number of payment gate config codes where requireCheckStatus = &#63;.
	 *
	 * @param requireCheckStatus the require check status
	 * @return the number of matching payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByrequireCheck(boolean requireCheckStatus)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_REQUIRECHECK;

		Object[] finderArgs = new Object[] { requireCheckStatus };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PAYMENTGATECONFIGCODE_WHERE);

			query.append(_FINDER_COLUMN_REQUIRECHECK_REQUIRECHECKSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(requireCheckStatus);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_REQUIRECHECK_REQUIRECHECKSTATUS_2 =
		"paymentGateConfigCode.requireCheckStatus = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_E_R = new FinderPath(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeModelImpl.FINDER_CACHE_ENABLED,
			PaymentGateConfigCodeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByE_R",
			new String[] {
				Integer.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_E_R = new FinderPath(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeModelImpl.FINDER_CACHE_ENABLED,
			PaymentGateConfigCodeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByE_R",
			new String[] { Integer.class.getName(), Boolean.class.getName() },
			PaymentGateConfigCodeModelImpl.ERRORCODE_COLUMN_BITMASK |
			PaymentGateConfigCodeModelImpl.REQUIRECHECKSTATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_E_R = new FinderPath(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByE_R",
			new String[] { Integer.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the payment gate config codes where errorCode = &#63; and requireCheckStatus = &#63;.
	 *
	 * @param errorCode the error code
	 * @param requireCheckStatus the require check status
	 * @return the matching payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PaymentGateConfigCode> findByE_R(int errorCode,
		boolean requireCheckStatus) throws SystemException {
		return findByE_R(errorCode, requireCheckStatus, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment gate config codes where errorCode = &#63; and requireCheckStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.paymentmgt.model.impl.PaymentGateConfigCodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param errorCode the error code
	 * @param requireCheckStatus the require check status
	 * @param start the lower bound of the range of payment gate config codes
	 * @param end the upper bound of the range of payment gate config codes (not inclusive)
	 * @return the range of matching payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PaymentGateConfigCode> findByE_R(int errorCode,
		boolean requireCheckStatus, int start, int end)
		throws SystemException {
		return findByE_R(errorCode, requireCheckStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment gate config codes where errorCode = &#63; and requireCheckStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.paymentmgt.model.impl.PaymentGateConfigCodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param errorCode the error code
	 * @param requireCheckStatus the require check status
	 * @param start the lower bound of the range of payment gate config codes
	 * @param end the upper bound of the range of payment gate config codes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PaymentGateConfigCode> findByE_R(int errorCode,
		boolean requireCheckStatus, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_E_R;
			finderArgs = new Object[] { errorCode, requireCheckStatus };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_E_R;
			finderArgs = new Object[] {
					errorCode, requireCheckStatus,
					
					start, end, orderByComparator
				};
		}

		List<PaymentGateConfigCode> list = (List<PaymentGateConfigCode>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PaymentGateConfigCode paymentGateConfigCode : list) {
				if ((errorCode != paymentGateConfigCode.getErrorCode()) ||
						(requireCheckStatus != paymentGateConfigCode.getRequireCheckStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_PAYMENTGATECONFIGCODE_WHERE);

			query.append(_FINDER_COLUMN_E_R_ERRORCODE_2);

			query.append(_FINDER_COLUMN_E_R_REQUIRECHECKSTATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PaymentGateConfigCodeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(errorCode);

				qPos.add(requireCheckStatus);

				if (!pagination) {
					list = (List<PaymentGateConfigCode>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<PaymentGateConfigCode>(list);
				}
				else {
					list = (List<PaymentGateConfigCode>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first payment gate config code in the ordered set where errorCode = &#63; and requireCheckStatus = &#63;.
	 *
	 * @param errorCode the error code
	 * @param requireCheckStatus the require check status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment gate config code
	 * @throws org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode findByE_R_First(int errorCode,
		boolean requireCheckStatus, OrderByComparator orderByComparator)
		throws NoSuchPaymentGateConfigCodeException, SystemException {
		PaymentGateConfigCode paymentGateConfigCode = fetchByE_R_First(errorCode,
				requireCheckStatus, orderByComparator);

		if (paymentGateConfigCode != null) {
			return paymentGateConfigCode;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("errorCode=");
		msg.append(errorCode);

		msg.append(", requireCheckStatus=");
		msg.append(requireCheckStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPaymentGateConfigCodeException(msg.toString());
	}

	/**
	 * Returns the first payment gate config code in the ordered set where errorCode = &#63; and requireCheckStatus = &#63;.
	 *
	 * @param errorCode the error code
	 * @param requireCheckStatus the require check status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment gate config code, or <code>null</code> if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode fetchByE_R_First(int errorCode,
		boolean requireCheckStatus, OrderByComparator orderByComparator)
		throws SystemException {
		List<PaymentGateConfigCode> list = findByE_R(errorCode,
				requireCheckStatus, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment gate config code in the ordered set where errorCode = &#63; and requireCheckStatus = &#63;.
	 *
	 * @param errorCode the error code
	 * @param requireCheckStatus the require check status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment gate config code
	 * @throws org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode findByE_R_Last(int errorCode,
		boolean requireCheckStatus, OrderByComparator orderByComparator)
		throws NoSuchPaymentGateConfigCodeException, SystemException {
		PaymentGateConfigCode paymentGateConfigCode = fetchByE_R_Last(errorCode,
				requireCheckStatus, orderByComparator);

		if (paymentGateConfigCode != null) {
			return paymentGateConfigCode;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("errorCode=");
		msg.append(errorCode);

		msg.append(", requireCheckStatus=");
		msg.append(requireCheckStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPaymentGateConfigCodeException(msg.toString());
	}

	/**
	 * Returns the last payment gate config code in the ordered set where errorCode = &#63; and requireCheckStatus = &#63;.
	 *
	 * @param errorCode the error code
	 * @param requireCheckStatus the require check status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment gate config code, or <code>null</code> if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode fetchByE_R_Last(int errorCode,
		boolean requireCheckStatus, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByE_R(errorCode, requireCheckStatus);

		if (count == 0) {
			return null;
		}

		List<PaymentGateConfigCode> list = findByE_R(errorCode,
				requireCheckStatus, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payment gate config codes before and after the current payment gate config code in the ordered set where errorCode = &#63; and requireCheckStatus = &#63;.
	 *
	 * @param payGateErrorId the primary key of the current payment gate config code
	 * @param errorCode the error code
	 * @param requireCheckStatus the require check status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment gate config code
	 * @throws org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException if a payment gate config code with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode[] findByE_R_PrevAndNext(long payGateErrorId,
		int errorCode, boolean requireCheckStatus,
		OrderByComparator orderByComparator)
		throws NoSuchPaymentGateConfigCodeException, SystemException {
		PaymentGateConfigCode paymentGateConfigCode = findByPrimaryKey(payGateErrorId);

		Session session = null;

		try {
			session = openSession();

			PaymentGateConfigCode[] array = new PaymentGateConfigCodeImpl[3];

			array[0] = getByE_R_PrevAndNext(session, paymentGateConfigCode,
					errorCode, requireCheckStatus, orderByComparator, true);

			array[1] = paymentGateConfigCode;

			array[2] = getByE_R_PrevAndNext(session, paymentGateConfigCode,
					errorCode, requireCheckStatus, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PaymentGateConfigCode getByE_R_PrevAndNext(Session session,
		PaymentGateConfigCode paymentGateConfigCode, int errorCode,
		boolean requireCheckStatus, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PAYMENTGATECONFIGCODE_WHERE);

		query.append(_FINDER_COLUMN_E_R_ERRORCODE_2);

		query.append(_FINDER_COLUMN_E_R_REQUIRECHECKSTATUS_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(PaymentGateConfigCodeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(errorCode);

		qPos.add(requireCheckStatus);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(paymentGateConfigCode);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PaymentGateConfigCode> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the payment gate config codes where errorCode = &#63; and requireCheckStatus = &#63; from the database.
	 *
	 * @param errorCode the error code
	 * @param requireCheckStatus the require check status
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByE_R(int errorCode, boolean requireCheckStatus)
		throws SystemException {
		for (PaymentGateConfigCode paymentGateConfigCode : findByE_R(
				errorCode, requireCheckStatus, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(paymentGateConfigCode);
		}
	}

	/**
	 * Returns the number of payment gate config codes where errorCode = &#63; and requireCheckStatus = &#63;.
	 *
	 * @param errorCode the error code
	 * @param requireCheckStatus the require check status
	 * @return the number of matching payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByE_R(int errorCode, boolean requireCheckStatus)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_E_R;

		Object[] finderArgs = new Object[] { errorCode, requireCheckStatus };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PAYMENTGATECONFIGCODE_WHERE);

			query.append(_FINDER_COLUMN_E_R_ERRORCODE_2);

			query.append(_FINDER_COLUMN_E_R_REQUIRECHECKSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(errorCode);

				qPos.add(requireCheckStatus);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_E_R_ERRORCODE_2 = "paymentGateConfigCode.errorCode = ? AND ";
	private static final String _FINDER_COLUMN_E_R_REQUIRECHECKSTATUS_2 = "paymentGateConfigCode.requireCheckStatus = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_E_PN_R = new FinderPath(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeModelImpl.FINDER_CACHE_ENABLED,
			PaymentGateConfigCodeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByE_PN_R",
			new String[] {
				Integer.class.getName(), String.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_E_PN_R =
		new FinderPath(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeModelImpl.FINDER_CACHE_ENABLED,
			PaymentGateConfigCodeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByE_PN_R",
			new String[] {
				Integer.class.getName(), String.class.getName(),
				Boolean.class.getName()
			},
			PaymentGateConfigCodeModelImpl.ERRORCODE_COLUMN_BITMASK |
			PaymentGateConfigCodeModelImpl.PAYMENTGATENAME_COLUMN_BITMASK |
			PaymentGateConfigCodeModelImpl.REQUIRECHECKSTATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_E_PN_R = new FinderPath(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByE_PN_R",
			new String[] {
				Integer.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the payment gate config codes where errorCode = &#63; and paymentGateName = &#63; and requireCheckStatus = &#63;.
	 *
	 * @param errorCode the error code
	 * @param paymentGateName the payment gate name
	 * @param requireCheckStatus the require check status
	 * @return the matching payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PaymentGateConfigCode> findByE_PN_R(int errorCode,
		String paymentGateName, boolean requireCheckStatus)
		throws SystemException {
		return findByE_PN_R(errorCode, paymentGateName, requireCheckStatus,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment gate config codes where errorCode = &#63; and paymentGateName = &#63; and requireCheckStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.paymentmgt.model.impl.PaymentGateConfigCodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param errorCode the error code
	 * @param paymentGateName the payment gate name
	 * @param requireCheckStatus the require check status
	 * @param start the lower bound of the range of payment gate config codes
	 * @param end the upper bound of the range of payment gate config codes (not inclusive)
	 * @return the range of matching payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PaymentGateConfigCode> findByE_PN_R(int errorCode,
		String paymentGateName, boolean requireCheckStatus, int start, int end)
		throws SystemException {
		return findByE_PN_R(errorCode, paymentGateName, requireCheckStatus,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment gate config codes where errorCode = &#63; and paymentGateName = &#63; and requireCheckStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.paymentmgt.model.impl.PaymentGateConfigCodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param errorCode the error code
	 * @param paymentGateName the payment gate name
	 * @param requireCheckStatus the require check status
	 * @param start the lower bound of the range of payment gate config codes
	 * @param end the upper bound of the range of payment gate config codes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PaymentGateConfigCode> findByE_PN_R(int errorCode,
		String paymentGateName, boolean requireCheckStatus, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_E_PN_R;
			finderArgs = new Object[] {
					errorCode, paymentGateName, requireCheckStatus
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_E_PN_R;
			finderArgs = new Object[] {
					errorCode, paymentGateName, requireCheckStatus,
					
					start, end, orderByComparator
				};
		}

		List<PaymentGateConfigCode> list = (List<PaymentGateConfigCode>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PaymentGateConfigCode paymentGateConfigCode : list) {
				if ((errorCode != paymentGateConfigCode.getErrorCode()) ||
						!Validator.equals(paymentGateName,
							paymentGateConfigCode.getPaymentGateName()) ||
						(requireCheckStatus != paymentGateConfigCode.getRequireCheckStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_PAYMENTGATECONFIGCODE_WHERE);

			query.append(_FINDER_COLUMN_E_PN_R_ERRORCODE_2);

			boolean bindPaymentGateName = false;

			if (paymentGateName == null) {
				query.append(_FINDER_COLUMN_E_PN_R_PAYMENTGATENAME_1);
			}
			else if (paymentGateName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_E_PN_R_PAYMENTGATENAME_3);
			}
			else {
				bindPaymentGateName = true;

				query.append(_FINDER_COLUMN_E_PN_R_PAYMENTGATENAME_2);
			}

			query.append(_FINDER_COLUMN_E_PN_R_REQUIRECHECKSTATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PaymentGateConfigCodeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(errorCode);

				if (bindPaymentGateName) {
					qPos.add(paymentGateName);
				}

				qPos.add(requireCheckStatus);

				if (!pagination) {
					list = (List<PaymentGateConfigCode>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<PaymentGateConfigCode>(list);
				}
				else {
					list = (List<PaymentGateConfigCode>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first payment gate config code in the ordered set where errorCode = &#63; and paymentGateName = &#63; and requireCheckStatus = &#63;.
	 *
	 * @param errorCode the error code
	 * @param paymentGateName the payment gate name
	 * @param requireCheckStatus the require check status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment gate config code
	 * @throws org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode findByE_PN_R_First(int errorCode,
		String paymentGateName, boolean requireCheckStatus,
		OrderByComparator orderByComparator)
		throws NoSuchPaymentGateConfigCodeException, SystemException {
		PaymentGateConfigCode paymentGateConfigCode = fetchByE_PN_R_First(errorCode,
				paymentGateName, requireCheckStatus, orderByComparator);

		if (paymentGateConfigCode != null) {
			return paymentGateConfigCode;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("errorCode=");
		msg.append(errorCode);

		msg.append(", paymentGateName=");
		msg.append(paymentGateName);

		msg.append(", requireCheckStatus=");
		msg.append(requireCheckStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPaymentGateConfigCodeException(msg.toString());
	}

	/**
	 * Returns the first payment gate config code in the ordered set where errorCode = &#63; and paymentGateName = &#63; and requireCheckStatus = &#63;.
	 *
	 * @param errorCode the error code
	 * @param paymentGateName the payment gate name
	 * @param requireCheckStatus the require check status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment gate config code, or <code>null</code> if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode fetchByE_PN_R_First(int errorCode,
		String paymentGateName, boolean requireCheckStatus,
		OrderByComparator orderByComparator) throws SystemException {
		List<PaymentGateConfigCode> list = findByE_PN_R(errorCode,
				paymentGateName, requireCheckStatus, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment gate config code in the ordered set where errorCode = &#63; and paymentGateName = &#63; and requireCheckStatus = &#63;.
	 *
	 * @param errorCode the error code
	 * @param paymentGateName the payment gate name
	 * @param requireCheckStatus the require check status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment gate config code
	 * @throws org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode findByE_PN_R_Last(int errorCode,
		String paymentGateName, boolean requireCheckStatus,
		OrderByComparator orderByComparator)
		throws NoSuchPaymentGateConfigCodeException, SystemException {
		PaymentGateConfigCode paymentGateConfigCode = fetchByE_PN_R_Last(errorCode,
				paymentGateName, requireCheckStatus, orderByComparator);

		if (paymentGateConfigCode != null) {
			return paymentGateConfigCode;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("errorCode=");
		msg.append(errorCode);

		msg.append(", paymentGateName=");
		msg.append(paymentGateName);

		msg.append(", requireCheckStatus=");
		msg.append(requireCheckStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPaymentGateConfigCodeException(msg.toString());
	}

	/**
	 * Returns the last payment gate config code in the ordered set where errorCode = &#63; and paymentGateName = &#63; and requireCheckStatus = &#63;.
	 *
	 * @param errorCode the error code
	 * @param paymentGateName the payment gate name
	 * @param requireCheckStatus the require check status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment gate config code, or <code>null</code> if a matching payment gate config code could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode fetchByE_PN_R_Last(int errorCode,
		String paymentGateName, boolean requireCheckStatus,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByE_PN_R(errorCode, paymentGateName, requireCheckStatus);

		if (count == 0) {
			return null;
		}

		List<PaymentGateConfigCode> list = findByE_PN_R(errorCode,
				paymentGateName, requireCheckStatus, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payment gate config codes before and after the current payment gate config code in the ordered set where errorCode = &#63; and paymentGateName = &#63; and requireCheckStatus = &#63;.
	 *
	 * @param payGateErrorId the primary key of the current payment gate config code
	 * @param errorCode the error code
	 * @param paymentGateName the payment gate name
	 * @param requireCheckStatus the require check status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment gate config code
	 * @throws org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException if a payment gate config code with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode[] findByE_PN_R_PrevAndNext(
		long payGateErrorId, int errorCode, String paymentGateName,
		boolean requireCheckStatus, OrderByComparator orderByComparator)
		throws NoSuchPaymentGateConfigCodeException, SystemException {
		PaymentGateConfigCode paymentGateConfigCode = findByPrimaryKey(payGateErrorId);

		Session session = null;

		try {
			session = openSession();

			PaymentGateConfigCode[] array = new PaymentGateConfigCodeImpl[3];

			array[0] = getByE_PN_R_PrevAndNext(session, paymentGateConfigCode,
					errorCode, paymentGateName, requireCheckStatus,
					orderByComparator, true);

			array[1] = paymentGateConfigCode;

			array[2] = getByE_PN_R_PrevAndNext(session, paymentGateConfigCode,
					errorCode, paymentGateName, requireCheckStatus,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PaymentGateConfigCode getByE_PN_R_PrevAndNext(Session session,
		PaymentGateConfigCode paymentGateConfigCode, int errorCode,
		String paymentGateName, boolean requireCheckStatus,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PAYMENTGATECONFIGCODE_WHERE);

		query.append(_FINDER_COLUMN_E_PN_R_ERRORCODE_2);

		boolean bindPaymentGateName = false;

		if (paymentGateName == null) {
			query.append(_FINDER_COLUMN_E_PN_R_PAYMENTGATENAME_1);
		}
		else if (paymentGateName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_E_PN_R_PAYMENTGATENAME_3);
		}
		else {
			bindPaymentGateName = true;

			query.append(_FINDER_COLUMN_E_PN_R_PAYMENTGATENAME_2);
		}

		query.append(_FINDER_COLUMN_E_PN_R_REQUIRECHECKSTATUS_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(PaymentGateConfigCodeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(errorCode);

		if (bindPaymentGateName) {
			qPos.add(paymentGateName);
		}

		qPos.add(requireCheckStatus);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(paymentGateConfigCode);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PaymentGateConfigCode> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the payment gate config codes where errorCode = &#63; and paymentGateName = &#63; and requireCheckStatus = &#63; from the database.
	 *
	 * @param errorCode the error code
	 * @param paymentGateName the payment gate name
	 * @param requireCheckStatus the require check status
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByE_PN_R(int errorCode, String paymentGateName,
		boolean requireCheckStatus) throws SystemException {
		for (PaymentGateConfigCode paymentGateConfigCode : findByE_PN_R(
				errorCode, paymentGateName, requireCheckStatus,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(paymentGateConfigCode);
		}
	}

	/**
	 * Returns the number of payment gate config codes where errorCode = &#63; and paymentGateName = &#63; and requireCheckStatus = &#63;.
	 *
	 * @param errorCode the error code
	 * @param paymentGateName the payment gate name
	 * @param requireCheckStatus the require check status
	 * @return the number of matching payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByE_PN_R(int errorCode, String paymentGateName,
		boolean requireCheckStatus) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_E_PN_R;

		Object[] finderArgs = new Object[] {
				errorCode, paymentGateName, requireCheckStatus
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_PAYMENTGATECONFIGCODE_WHERE);

			query.append(_FINDER_COLUMN_E_PN_R_ERRORCODE_2);

			boolean bindPaymentGateName = false;

			if (paymentGateName == null) {
				query.append(_FINDER_COLUMN_E_PN_R_PAYMENTGATENAME_1);
			}
			else if (paymentGateName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_E_PN_R_PAYMENTGATENAME_3);
			}
			else {
				bindPaymentGateName = true;

				query.append(_FINDER_COLUMN_E_PN_R_PAYMENTGATENAME_2);
			}

			query.append(_FINDER_COLUMN_E_PN_R_REQUIRECHECKSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(errorCode);

				if (bindPaymentGateName) {
					qPos.add(paymentGateName);
				}

				qPos.add(requireCheckStatus);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_E_PN_R_ERRORCODE_2 = "paymentGateConfigCode.errorCode = ? AND ";
	private static final String _FINDER_COLUMN_E_PN_R_PAYMENTGATENAME_1 = "paymentGateConfigCode.paymentGateName IS NULL AND ";
	private static final String _FINDER_COLUMN_E_PN_R_PAYMENTGATENAME_2 = "paymentGateConfigCode.paymentGateName = ? AND ";
	private static final String _FINDER_COLUMN_E_PN_R_PAYMENTGATENAME_3 = "(paymentGateConfigCode.paymentGateName IS NULL OR paymentGateConfigCode.paymentGateName = '') AND ";
	private static final String _FINDER_COLUMN_E_PN_R_REQUIRECHECKSTATUS_2 = "paymentGateConfigCode.requireCheckStatus = ?";

	public PaymentGateConfigCodePersistenceImpl() {
		setModelClass(PaymentGateConfigCode.class);
	}

	/**
	 * Caches the payment gate config code in the entity cache if it is enabled.
	 *
	 * @param paymentGateConfigCode the payment gate config code
	 */
	@Override
	public void cacheResult(PaymentGateConfigCode paymentGateConfigCode) {
		EntityCacheUtil.putResult(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeImpl.class,
			paymentGateConfigCode.getPrimaryKey(), paymentGateConfigCode);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_E_P,
			new Object[] {
				paymentGateConfigCode.getErrorCode(),
				paymentGateConfigCode.getPaymentGateName()
			}, paymentGateConfigCode);

		paymentGateConfigCode.resetOriginalValues();
	}

	/**
	 * Caches the payment gate config codes in the entity cache if it is enabled.
	 *
	 * @param paymentGateConfigCodes the payment gate config codes
	 */
	@Override
	public void cacheResult(List<PaymentGateConfigCode> paymentGateConfigCodes) {
		for (PaymentGateConfigCode paymentGateConfigCode : paymentGateConfigCodes) {
			if (EntityCacheUtil.getResult(
						PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
						PaymentGateConfigCodeImpl.class,
						paymentGateConfigCode.getPrimaryKey()) == null) {
				cacheResult(paymentGateConfigCode);
			}
			else {
				paymentGateConfigCode.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all payment gate config codes.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(PaymentGateConfigCodeImpl.class.getName());
		}

		EntityCacheUtil.clearCache(PaymentGateConfigCodeImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the payment gate config code.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PaymentGateConfigCode paymentGateConfigCode) {
		EntityCacheUtil.removeResult(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeImpl.class,
			paymentGateConfigCode.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(paymentGateConfigCode);
	}

	@Override
	public void clearCache(List<PaymentGateConfigCode> paymentGateConfigCodes) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PaymentGateConfigCode paymentGateConfigCode : paymentGateConfigCodes) {
			EntityCacheUtil.removeResult(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
				PaymentGateConfigCodeImpl.class,
				paymentGateConfigCode.getPrimaryKey());

			clearUniqueFindersCache(paymentGateConfigCode);
		}
	}

	protected void cacheUniqueFindersCache(
		PaymentGateConfigCode paymentGateConfigCode) {
		if (paymentGateConfigCode.isNew()) {
			Object[] args = new Object[] {
					paymentGateConfigCode.getErrorCode(),
					paymentGateConfigCode.getPaymentGateName()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_E_P, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_E_P, args,
				paymentGateConfigCode);
		}
		else {
			PaymentGateConfigCodeModelImpl paymentGateConfigCodeModelImpl = (PaymentGateConfigCodeModelImpl)paymentGateConfigCode;

			if ((paymentGateConfigCodeModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_E_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentGateConfigCode.getErrorCode(),
						paymentGateConfigCode.getPaymentGateName()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_E_P, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_E_P, args,
					paymentGateConfigCode);
			}
		}
	}

	protected void clearUniqueFindersCache(
		PaymentGateConfigCode paymentGateConfigCode) {
		PaymentGateConfigCodeModelImpl paymentGateConfigCodeModelImpl = (PaymentGateConfigCodeModelImpl)paymentGateConfigCode;

		Object[] args = new Object[] {
				paymentGateConfigCode.getErrorCode(),
				paymentGateConfigCode.getPaymentGateName()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_E_P, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_E_P, args);

		if ((paymentGateConfigCodeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_E_P.getColumnBitmask()) != 0) {
			args = new Object[] {
					paymentGateConfigCodeModelImpl.getOriginalErrorCode(),
					paymentGateConfigCodeModelImpl.getOriginalPaymentGateName()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_E_P, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_E_P, args);
		}
	}

	/**
	 * Creates a new payment gate config code with the primary key. Does not add the payment gate config code to the database.
	 *
	 * @param payGateErrorId the primary key for the new payment gate config code
	 * @return the new payment gate config code
	 */
	@Override
	public PaymentGateConfigCode create(long payGateErrorId) {
		PaymentGateConfigCode paymentGateConfigCode = new PaymentGateConfigCodeImpl();

		paymentGateConfigCode.setNew(true);
		paymentGateConfigCode.setPrimaryKey(payGateErrorId);

		return paymentGateConfigCode;
	}

	/**
	 * Removes the payment gate config code with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param payGateErrorId the primary key of the payment gate config code
	 * @return the payment gate config code that was removed
	 * @throws org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException if a payment gate config code with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode remove(long payGateErrorId)
		throws NoSuchPaymentGateConfigCodeException, SystemException {
		return remove((Serializable)payGateErrorId);
	}

	/**
	 * Removes the payment gate config code with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the payment gate config code
	 * @return the payment gate config code that was removed
	 * @throws org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException if a payment gate config code with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode remove(Serializable primaryKey)
		throws NoSuchPaymentGateConfigCodeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			PaymentGateConfigCode paymentGateConfigCode = (PaymentGateConfigCode)session.get(PaymentGateConfigCodeImpl.class,
					primaryKey);

			if (paymentGateConfigCode == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPaymentGateConfigCodeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(paymentGateConfigCode);
		}
		catch (NoSuchPaymentGateConfigCodeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected PaymentGateConfigCode removeImpl(
		PaymentGateConfigCode paymentGateConfigCode) throws SystemException {
		paymentGateConfigCode = toUnwrappedModel(paymentGateConfigCode);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(paymentGateConfigCode)) {
				paymentGateConfigCode = (PaymentGateConfigCode)session.get(PaymentGateConfigCodeImpl.class,
						paymentGateConfigCode.getPrimaryKeyObj());
			}

			if (paymentGateConfigCode != null) {
				session.delete(paymentGateConfigCode);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (paymentGateConfigCode != null) {
			clearCache(paymentGateConfigCode);
		}

		return paymentGateConfigCode;
	}

	@Override
	public PaymentGateConfigCode updateImpl(
		org.opencps.paymentmgt.model.PaymentGateConfigCode paymentGateConfigCode)
		throws SystemException {
		paymentGateConfigCode = toUnwrappedModel(paymentGateConfigCode);

		boolean isNew = paymentGateConfigCode.isNew();

		PaymentGateConfigCodeModelImpl paymentGateConfigCodeModelImpl = (PaymentGateConfigCodeModelImpl)paymentGateConfigCode;

		Session session = null;

		try {
			session = openSession();

			if (paymentGateConfigCode.isNew()) {
				session.save(paymentGateConfigCode);

				paymentGateConfigCode.setNew(false);
			}
			else {
				session.merge(paymentGateConfigCode);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PaymentGateConfigCodeModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((paymentGateConfigCodeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTGATEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentGateConfigCodeModelImpl.getOriginalPaymentGateId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PAYMENTGATEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTGATEID,
					args);

				args = new Object[] {
						paymentGateConfigCodeModelImpl.getPaymentGateId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PAYMENTGATEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTGATEID,
					args);
			}

			if ((paymentGateConfigCodeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTGATENAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentGateConfigCodeModelImpl.getOriginalPaymentGateName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PAYMENTGATENAME,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTGATENAME,
					args);

				args = new Object[] {
						paymentGateConfigCodeModelImpl.getPaymentGateName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PAYMENTGATENAME,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTGATENAME,
					args);
			}

			if ((paymentGateConfigCodeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REQUIRECHECK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentGateConfigCodeModelImpl.getOriginalRequireCheckStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REQUIRECHECK,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REQUIRECHECK,
					args);

				args = new Object[] {
						paymentGateConfigCodeModelImpl.getRequireCheckStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REQUIRECHECK,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REQUIRECHECK,
					args);
			}

			if ((paymentGateConfigCodeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_E_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentGateConfigCodeModelImpl.getOriginalErrorCode(),
						paymentGateConfigCodeModelImpl.getOriginalRequireCheckStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_E_R, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_E_R,
					args);

				args = new Object[] {
						paymentGateConfigCodeModelImpl.getErrorCode(),
						paymentGateConfigCodeModelImpl.getRequireCheckStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_E_R, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_E_R,
					args);
			}

			if ((paymentGateConfigCodeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_E_PN_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentGateConfigCodeModelImpl.getOriginalErrorCode(),
						paymentGateConfigCodeModelImpl.getOriginalPaymentGateName(),
						paymentGateConfigCodeModelImpl.getOriginalRequireCheckStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_E_PN_R, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_E_PN_R,
					args);

				args = new Object[] {
						paymentGateConfigCodeModelImpl.getErrorCode(),
						paymentGateConfigCodeModelImpl.getPaymentGateName(),
						paymentGateConfigCodeModelImpl.getRequireCheckStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_E_PN_R, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_E_PN_R,
					args);
			}
		}

		EntityCacheUtil.putResult(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
			PaymentGateConfigCodeImpl.class,
			paymentGateConfigCode.getPrimaryKey(), paymentGateConfigCode);

		clearUniqueFindersCache(paymentGateConfigCode);
		cacheUniqueFindersCache(paymentGateConfigCode);

		return paymentGateConfigCode;
	}

	protected PaymentGateConfigCode toUnwrappedModel(
		PaymentGateConfigCode paymentGateConfigCode) {
		if (paymentGateConfigCode instanceof PaymentGateConfigCodeImpl) {
			return paymentGateConfigCode;
		}

		PaymentGateConfigCodeImpl paymentGateConfigCodeImpl = new PaymentGateConfigCodeImpl();

		paymentGateConfigCodeImpl.setNew(paymentGateConfigCode.isNew());
		paymentGateConfigCodeImpl.setPrimaryKey(paymentGateConfigCode.getPrimaryKey());

		paymentGateConfigCodeImpl.setPayGateErrorId(paymentGateConfigCode.getPayGateErrorId());
		paymentGateConfigCodeImpl.setErrorCode(paymentGateConfigCode.getErrorCode());
		paymentGateConfigCodeImpl.setDescription(paymentGateConfigCode.getDescription());
		paymentGateConfigCodeImpl.setPaymentGateId(paymentGateConfigCode.getPaymentGateId());
		paymentGateConfigCodeImpl.setPaymentGateName(paymentGateConfigCode.getPaymentGateName());
		paymentGateConfigCodeImpl.setRequireCheckStatus(paymentGateConfigCode.isRequireCheckStatus());

		return paymentGateConfigCodeImpl;
	}

	/**
	 * Returns the payment gate config code with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the payment gate config code
	 * @return the payment gate config code
	 * @throws org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException if a payment gate config code with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPaymentGateConfigCodeException, SystemException {
		PaymentGateConfigCode paymentGateConfigCode = fetchByPrimaryKey(primaryKey);

		if (paymentGateConfigCode == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPaymentGateConfigCodeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return paymentGateConfigCode;
	}

	/**
	 * Returns the payment gate config code with the primary key or throws a {@link org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException} if it could not be found.
	 *
	 * @param payGateErrorId the primary key of the payment gate config code
	 * @return the payment gate config code
	 * @throws org.opencps.paymentmgt.NoSuchPaymentGateConfigCodeException if a payment gate config code with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode findByPrimaryKey(long payGateErrorId)
		throws NoSuchPaymentGateConfigCodeException, SystemException {
		return findByPrimaryKey((Serializable)payGateErrorId);
	}

	/**
	 * Returns the payment gate config code with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the payment gate config code
	 * @return the payment gate config code, or <code>null</code> if a payment gate config code with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		PaymentGateConfigCode paymentGateConfigCode = (PaymentGateConfigCode)EntityCacheUtil.getResult(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
				PaymentGateConfigCodeImpl.class, primaryKey);

		if (paymentGateConfigCode == _nullPaymentGateConfigCode) {
			return null;
		}

		if (paymentGateConfigCode == null) {
			Session session = null;

			try {
				session = openSession();

				paymentGateConfigCode = (PaymentGateConfigCode)session.get(PaymentGateConfigCodeImpl.class,
						primaryKey);

				if (paymentGateConfigCode != null) {
					cacheResult(paymentGateConfigCode);
				}
				else {
					EntityCacheUtil.putResult(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
						PaymentGateConfigCodeImpl.class, primaryKey,
						_nullPaymentGateConfigCode);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(PaymentGateConfigCodeModelImpl.ENTITY_CACHE_ENABLED,
					PaymentGateConfigCodeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return paymentGateConfigCode;
	}

	/**
	 * Returns the payment gate config code with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param payGateErrorId the primary key of the payment gate config code
	 * @return the payment gate config code, or <code>null</code> if a payment gate config code with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentGateConfigCode fetchByPrimaryKey(long payGateErrorId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)payGateErrorId);
	}

	/**
	 * Returns all the payment gate config codes.
	 *
	 * @return the payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PaymentGateConfigCode> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment gate config codes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.paymentmgt.model.impl.PaymentGateConfigCodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of payment gate config codes
	 * @param end the upper bound of the range of payment gate config codes (not inclusive)
	 * @return the range of payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PaymentGateConfigCode> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment gate config codes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.paymentmgt.model.impl.PaymentGateConfigCodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of payment gate config codes
	 * @param end the upper bound of the range of payment gate config codes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PaymentGateConfigCode> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<PaymentGateConfigCode> list = (List<PaymentGateConfigCode>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PAYMENTGATECONFIGCODE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PAYMENTGATECONFIGCODE;

				if (pagination) {
					sql = sql.concat(PaymentGateConfigCodeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PaymentGateConfigCode>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<PaymentGateConfigCode>(list);
				}
				else {
					list = (List<PaymentGateConfigCode>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the payment gate config codes from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (PaymentGateConfigCode paymentGateConfigCode : findAll()) {
			remove(paymentGateConfigCode);
		}
	}

	/**
	 * Returns the number of payment gate config codes.
	 *
	 * @return the number of payment gate config codes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PAYMENTGATECONFIGCODE);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the payment gate config code persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.opencps.paymentmgt.model.PaymentGateConfigCode")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<PaymentGateConfigCode>> listenersList = new ArrayList<ModelListener<PaymentGateConfigCode>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<PaymentGateConfigCode>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(PaymentGateConfigCodeImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_PAYMENTGATECONFIGCODE = "SELECT paymentGateConfigCode FROM PaymentGateConfigCode paymentGateConfigCode";
	private static final String _SQL_SELECT_PAYMENTGATECONFIGCODE_WHERE = "SELECT paymentGateConfigCode FROM PaymentGateConfigCode paymentGateConfigCode WHERE ";
	private static final String _SQL_COUNT_PAYMENTGATECONFIGCODE = "SELECT COUNT(paymentGateConfigCode) FROM PaymentGateConfigCode paymentGateConfigCode";
	private static final String _SQL_COUNT_PAYMENTGATECONFIGCODE_WHERE = "SELECT COUNT(paymentGateConfigCode) FROM PaymentGateConfigCode paymentGateConfigCode WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "paymentGateConfigCode.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PaymentGateConfigCode exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PaymentGateConfigCode exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(PaymentGateConfigCodePersistenceImpl.class);
	private static PaymentGateConfigCode _nullPaymentGateConfigCode = new PaymentGateConfigCodeImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<PaymentGateConfigCode> toCacheModel() {
				return _nullPaymentGateConfigCodeCacheModel;
			}
		};

	private static CacheModel<PaymentGateConfigCode> _nullPaymentGateConfigCodeCacheModel =
		new CacheModel<PaymentGateConfigCode>() {
			@Override
			public PaymentGateConfigCode toEntityModel() {
				return _nullPaymentGateConfigCode;
			}
		};
}