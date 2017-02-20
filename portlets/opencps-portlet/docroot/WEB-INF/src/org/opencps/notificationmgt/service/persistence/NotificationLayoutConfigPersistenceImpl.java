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

package org.opencps.notificationmgt.service.persistence;

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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.opencps.notificationmgt.NoSuchNotificationLayoutConfigException;
import org.opencps.notificationmgt.model.NotificationLayoutConfig;
import org.opencps.notificationmgt.model.impl.NotificationLayoutConfigImpl;
import org.opencps.notificationmgt.model.impl.NotificationLayoutConfigModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the notification layout config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author nhanhoang
 * @see NotificationLayoutConfigPersistence
 * @see NotificationLayoutConfigUtil
 * @generated
 */
public class NotificationLayoutConfigPersistenceImpl extends BasePersistenceImpl<NotificationLayoutConfig>
	implements NotificationLayoutConfigPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NotificationLayoutConfigUtil} to access the notification layout config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NotificationLayoutConfigImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NotificationLayoutConfigModelImpl.ENTITY_CACHE_ENABLED,
			NotificationLayoutConfigModelImpl.FINDER_CACHE_ENABLED,
			NotificationLayoutConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NotificationLayoutConfigModelImpl.ENTITY_CACHE_ENABLED,
			NotificationLayoutConfigModelImpl.FINDER_CACHE_ENABLED,
			NotificationLayoutConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NotificationLayoutConfigModelImpl.ENTITY_CACHE_ENABLED,
			NotificationLayoutConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_N_D_E = new FinderPath(NotificationLayoutConfigModelImpl.ENTITY_CACHE_ENABLED,
			NotificationLayoutConfigModelImpl.FINDER_CACHE_ENABLED,
			NotificationLayoutConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByN_D_E",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_N_D_E = new FinderPath(NotificationLayoutConfigModelImpl.ENTITY_CACHE_ENABLED,
			NotificationLayoutConfigModelImpl.FINDER_CACHE_ENABLED,
			NotificationLayoutConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByN_D_E",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			NotificationLayoutConfigModelImpl.NOTIFICATIONCONFIGID_COLUMN_BITMASK |
			NotificationLayoutConfigModelImpl.DICTITEMID_COLUMN_BITMASK |
			NotificationLayoutConfigModelImpl.ISEMPLOYEE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_N_D_E = new FinderPath(NotificationLayoutConfigModelImpl.ENTITY_CACHE_ENABLED,
			NotificationLayoutConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByN_D_E",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the notification layout configs where notificationConfigId = &#63; and dictItemId = &#63; and isEmployee = &#63;.
	 *
	 * @param notificationConfigId the notification config ID
	 * @param dictItemId the dict item ID
	 * @param isEmployee the is employee
	 * @return the matching notification layout configs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<NotificationLayoutConfig> findByN_D_E(
		long notificationConfigId, long dictItemId, boolean isEmployee)
		throws SystemException {
		return findByN_D_E(notificationConfigId, dictItemId, isEmployee,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notification layout configs where notificationConfigId = &#63; and dictItemId = &#63; and isEmployee = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.notificationmgt.model.impl.NotificationLayoutConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param notificationConfigId the notification config ID
	 * @param dictItemId the dict item ID
	 * @param isEmployee the is employee
	 * @param start the lower bound of the range of notification layout configs
	 * @param end the upper bound of the range of notification layout configs (not inclusive)
	 * @return the range of matching notification layout configs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<NotificationLayoutConfig> findByN_D_E(
		long notificationConfigId, long dictItemId, boolean isEmployee,
		int start, int end) throws SystemException {
		return findByN_D_E(notificationConfigId, dictItemId, isEmployee, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the notification layout configs where notificationConfigId = &#63; and dictItemId = &#63; and isEmployee = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.notificationmgt.model.impl.NotificationLayoutConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param notificationConfigId the notification config ID
	 * @param dictItemId the dict item ID
	 * @param isEmployee the is employee
	 * @param start the lower bound of the range of notification layout configs
	 * @param end the upper bound of the range of notification layout configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification layout configs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<NotificationLayoutConfig> findByN_D_E(
		long notificationConfigId, long dictItemId, boolean isEmployee,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_N_D_E;
			finderArgs = new Object[] {
					notificationConfigId, dictItemId, isEmployee
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_N_D_E;
			finderArgs = new Object[] {
					notificationConfigId, dictItemId, isEmployee,
					
					start, end, orderByComparator
				};
		}

		List<NotificationLayoutConfig> list = (List<NotificationLayoutConfig>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (NotificationLayoutConfig notificationLayoutConfig : list) {
				if ((notificationConfigId != notificationLayoutConfig.getNotificationConfigId()) ||
						(dictItemId != notificationLayoutConfig.getDictItemId()) ||
						(isEmployee != notificationLayoutConfig.getIsEmployee())) {
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

			query.append(_SQL_SELECT_NOTIFICATIONLAYOUTCONFIG_WHERE);

			query.append(_FINDER_COLUMN_N_D_E_NOTIFICATIONCONFIGID_2);

			query.append(_FINDER_COLUMN_N_D_E_DICTITEMID_2);

			query.append(_FINDER_COLUMN_N_D_E_ISEMPLOYEE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(NotificationLayoutConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(notificationConfigId);

				qPos.add(dictItemId);

				qPos.add(isEmployee);

				if (!pagination) {
					list = (List<NotificationLayoutConfig>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<NotificationLayoutConfig>(list);
				}
				else {
					list = (List<NotificationLayoutConfig>)QueryUtil.list(q,
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
	 * Returns the first notification layout config in the ordered set where notificationConfigId = &#63; and dictItemId = &#63; and isEmployee = &#63;.
	 *
	 * @param notificationConfigId the notification config ID
	 * @param dictItemId the dict item ID
	 * @param isEmployee the is employee
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification layout config
	 * @throws org.opencps.notificationmgt.NoSuchNotificationLayoutConfigException if a matching notification layout config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotificationLayoutConfig findByN_D_E_First(
		long notificationConfigId, long dictItemId, boolean isEmployee,
		OrderByComparator orderByComparator)
		throws NoSuchNotificationLayoutConfigException, SystemException {
		NotificationLayoutConfig notificationLayoutConfig = fetchByN_D_E_First(notificationConfigId,
				dictItemId, isEmployee, orderByComparator);

		if (notificationLayoutConfig != null) {
			return notificationLayoutConfig;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("notificationConfigId=");
		msg.append(notificationConfigId);

		msg.append(", dictItemId=");
		msg.append(dictItemId);

		msg.append(", isEmployee=");
		msg.append(isEmployee);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationLayoutConfigException(msg.toString());
	}

	/**
	 * Returns the first notification layout config in the ordered set where notificationConfigId = &#63; and dictItemId = &#63; and isEmployee = &#63;.
	 *
	 * @param notificationConfigId the notification config ID
	 * @param dictItemId the dict item ID
	 * @param isEmployee the is employee
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification layout config, or <code>null</code> if a matching notification layout config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotificationLayoutConfig fetchByN_D_E_First(
		long notificationConfigId, long dictItemId, boolean isEmployee,
		OrderByComparator orderByComparator) throws SystemException {
		List<NotificationLayoutConfig> list = findByN_D_E(notificationConfigId,
				dictItemId, isEmployee, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notification layout config in the ordered set where notificationConfigId = &#63; and dictItemId = &#63; and isEmployee = &#63;.
	 *
	 * @param notificationConfigId the notification config ID
	 * @param dictItemId the dict item ID
	 * @param isEmployee the is employee
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification layout config
	 * @throws org.opencps.notificationmgt.NoSuchNotificationLayoutConfigException if a matching notification layout config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotificationLayoutConfig findByN_D_E_Last(
		long notificationConfigId, long dictItemId, boolean isEmployee,
		OrderByComparator orderByComparator)
		throws NoSuchNotificationLayoutConfigException, SystemException {
		NotificationLayoutConfig notificationLayoutConfig = fetchByN_D_E_Last(notificationConfigId,
				dictItemId, isEmployee, orderByComparator);

		if (notificationLayoutConfig != null) {
			return notificationLayoutConfig;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("notificationConfigId=");
		msg.append(notificationConfigId);

		msg.append(", dictItemId=");
		msg.append(dictItemId);

		msg.append(", isEmployee=");
		msg.append(isEmployee);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationLayoutConfigException(msg.toString());
	}

	/**
	 * Returns the last notification layout config in the ordered set where notificationConfigId = &#63; and dictItemId = &#63; and isEmployee = &#63;.
	 *
	 * @param notificationConfigId the notification config ID
	 * @param dictItemId the dict item ID
	 * @param isEmployee the is employee
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification layout config, or <code>null</code> if a matching notification layout config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotificationLayoutConfig fetchByN_D_E_Last(
		long notificationConfigId, long dictItemId, boolean isEmployee,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByN_D_E(notificationConfigId, dictItemId, isEmployee);

		if (count == 0) {
			return null;
		}

		List<NotificationLayoutConfig> list = findByN_D_E(notificationConfigId,
				dictItemId, isEmployee, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notification layout configs before and after the current notification layout config in the ordered set where notificationConfigId = &#63; and dictItemId = &#63; and isEmployee = &#63;.
	 *
	 * @param notificationLayoutConfigId the primary key of the current notification layout config
	 * @param notificationConfigId the notification config ID
	 * @param dictItemId the dict item ID
	 * @param isEmployee the is employee
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification layout config
	 * @throws org.opencps.notificationmgt.NoSuchNotificationLayoutConfigException if a notification layout config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotificationLayoutConfig[] findByN_D_E_PrevAndNext(
		long notificationLayoutConfigId, long notificationConfigId,
		long dictItemId, boolean isEmployee, OrderByComparator orderByComparator)
		throws NoSuchNotificationLayoutConfigException, SystemException {
		NotificationLayoutConfig notificationLayoutConfig = findByPrimaryKey(notificationLayoutConfigId);

		Session session = null;

		try {
			session = openSession();

			NotificationLayoutConfig[] array = new NotificationLayoutConfigImpl[3];

			array[0] = getByN_D_E_PrevAndNext(session,
					notificationLayoutConfig, notificationConfigId, dictItemId,
					isEmployee, orderByComparator, true);

			array[1] = notificationLayoutConfig;

			array[2] = getByN_D_E_PrevAndNext(session,
					notificationLayoutConfig, notificationConfigId, dictItemId,
					isEmployee, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected NotificationLayoutConfig getByN_D_E_PrevAndNext(Session session,
		NotificationLayoutConfig notificationLayoutConfig,
		long notificationConfigId, long dictItemId, boolean isEmployee,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_NOTIFICATIONLAYOUTCONFIG_WHERE);

		query.append(_FINDER_COLUMN_N_D_E_NOTIFICATIONCONFIGID_2);

		query.append(_FINDER_COLUMN_N_D_E_DICTITEMID_2);

		query.append(_FINDER_COLUMN_N_D_E_ISEMPLOYEE_2);

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
			query.append(NotificationLayoutConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(notificationConfigId);

		qPos.add(dictItemId);

		qPos.add(isEmployee);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(notificationLayoutConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<NotificationLayoutConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notification layout configs where notificationConfigId = &#63; and dictItemId = &#63; and isEmployee = &#63; from the database.
	 *
	 * @param notificationConfigId the notification config ID
	 * @param dictItemId the dict item ID
	 * @param isEmployee the is employee
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByN_D_E(long notificationConfigId, long dictItemId,
		boolean isEmployee) throws SystemException {
		for (NotificationLayoutConfig notificationLayoutConfig : findByN_D_E(
				notificationConfigId, dictItemId, isEmployee,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(notificationLayoutConfig);
		}
	}

	/**
	 * Returns the number of notification layout configs where notificationConfigId = &#63; and dictItemId = &#63; and isEmployee = &#63;.
	 *
	 * @param notificationConfigId the notification config ID
	 * @param dictItemId the dict item ID
	 * @param isEmployee the is employee
	 * @return the number of matching notification layout configs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByN_D_E(long notificationConfigId, long dictItemId,
		boolean isEmployee) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_N_D_E;

		Object[] finderArgs = new Object[] {
				notificationConfigId, dictItemId, isEmployee
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_NOTIFICATIONLAYOUTCONFIG_WHERE);

			query.append(_FINDER_COLUMN_N_D_E_NOTIFICATIONCONFIGID_2);

			query.append(_FINDER_COLUMN_N_D_E_DICTITEMID_2);

			query.append(_FINDER_COLUMN_N_D_E_ISEMPLOYEE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(notificationConfigId);

				qPos.add(dictItemId);

				qPos.add(isEmployee);

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

	private static final String _FINDER_COLUMN_N_D_E_NOTIFICATIONCONFIGID_2 = "notificationLayoutConfig.notificationConfigId = ? AND ";
	private static final String _FINDER_COLUMN_N_D_E_DICTITEMID_2 = "notificationLayoutConfig.dictItemId = ? AND ";
	private static final String _FINDER_COLUMN_N_D_E_ISEMPLOYEE_2 = "notificationLayoutConfig.isEmployee = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_NOTIFICATIONCONFIGID = new FinderPath(NotificationLayoutConfigModelImpl.ENTITY_CACHE_ENABLED,
			NotificationLayoutConfigModelImpl.FINDER_CACHE_ENABLED,
			NotificationLayoutConfigImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByNotificationConfigId",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			},
			NotificationLayoutConfigModelImpl.NOTIFICATIONCONFIGID_COLUMN_BITMASK |
			NotificationLayoutConfigModelImpl.ISEMPLOYEE_COLUMN_BITMASK |
			NotificationLayoutConfigModelImpl.ISPAYMENT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NOTIFICATIONCONFIGID = new FinderPath(NotificationLayoutConfigModelImpl.ENTITY_CACHE_ENABLED,
			NotificationLayoutConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByNotificationConfigId",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns the notification layout config where notificationConfigId = &#63; and isEmployee = &#63; and isPayment = &#63; or throws a {@link org.opencps.notificationmgt.NoSuchNotificationLayoutConfigException} if it could not be found.
	 *
	 * @param notificationConfigId the notification config ID
	 * @param isEmployee the is employee
	 * @param isPayment the is payment
	 * @return the matching notification layout config
	 * @throws org.opencps.notificationmgt.NoSuchNotificationLayoutConfigException if a matching notification layout config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotificationLayoutConfig findByNotificationConfigId(
		long notificationConfigId, boolean isEmployee, boolean isPayment)
		throws NoSuchNotificationLayoutConfigException, SystemException {
		NotificationLayoutConfig notificationLayoutConfig = fetchByNotificationConfigId(notificationConfigId,
				isEmployee, isPayment);

		if (notificationLayoutConfig == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("notificationConfigId=");
			msg.append(notificationConfigId);

			msg.append(", isEmployee=");
			msg.append(isEmployee);

			msg.append(", isPayment=");
			msg.append(isPayment);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchNotificationLayoutConfigException(msg.toString());
		}

		return notificationLayoutConfig;
	}

	/**
	 * Returns the notification layout config where notificationConfigId = &#63; and isEmployee = &#63; and isPayment = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param notificationConfigId the notification config ID
	 * @param isEmployee the is employee
	 * @param isPayment the is payment
	 * @return the matching notification layout config, or <code>null</code> if a matching notification layout config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotificationLayoutConfig fetchByNotificationConfigId(
		long notificationConfigId, boolean isEmployee, boolean isPayment)
		throws SystemException {
		return fetchByNotificationConfigId(notificationConfigId, isEmployee,
			isPayment, true);
	}

	/**
	 * Returns the notification layout config where notificationConfigId = &#63; and isEmployee = &#63; and isPayment = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param notificationConfigId the notification config ID
	 * @param isEmployee the is employee
	 * @param isPayment the is payment
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching notification layout config, or <code>null</code> if a matching notification layout config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotificationLayoutConfig fetchByNotificationConfigId(
		long notificationConfigId, boolean isEmployee, boolean isPayment,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] {
				notificationConfigId, isEmployee, isPayment
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_NOTIFICATIONCONFIGID,
					finderArgs, this);
		}

		if (result instanceof NotificationLayoutConfig) {
			NotificationLayoutConfig notificationLayoutConfig = (NotificationLayoutConfig)result;

			if ((notificationConfigId != notificationLayoutConfig.getNotificationConfigId()) ||
					(isEmployee != notificationLayoutConfig.getIsEmployee()) ||
					(isPayment != notificationLayoutConfig.getIsPayment())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_NOTIFICATIONLAYOUTCONFIG_WHERE);

			query.append(_FINDER_COLUMN_NOTIFICATIONCONFIGID_NOTIFICATIONCONFIGID_2);

			query.append(_FINDER_COLUMN_NOTIFICATIONCONFIGID_ISEMPLOYEE_2);

			query.append(_FINDER_COLUMN_NOTIFICATIONCONFIGID_ISPAYMENT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(notificationConfigId);

				qPos.add(isEmployee);

				qPos.add(isPayment);

				List<NotificationLayoutConfig> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NOTIFICATIONCONFIGID,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"NotificationLayoutConfigPersistenceImpl.fetchByNotificationConfigId(long, boolean, boolean, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					NotificationLayoutConfig notificationLayoutConfig = list.get(0);

					result = notificationLayoutConfig;

					cacheResult(notificationLayoutConfig);

					if ((notificationLayoutConfig.getNotificationConfigId() != notificationConfigId) ||
							(notificationLayoutConfig.getIsEmployee() != isEmployee) ||
							(notificationLayoutConfig.getIsPayment() != isPayment)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NOTIFICATIONCONFIGID,
							finderArgs, notificationLayoutConfig);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NOTIFICATIONCONFIGID,
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
			return (NotificationLayoutConfig)result;
		}
	}

	/**
	 * Removes the notification layout config where notificationConfigId = &#63; and isEmployee = &#63; and isPayment = &#63; from the database.
	 *
	 * @param notificationConfigId the notification config ID
	 * @param isEmployee the is employee
	 * @param isPayment the is payment
	 * @return the notification layout config that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotificationLayoutConfig removeByNotificationConfigId(
		long notificationConfigId, boolean isEmployee, boolean isPayment)
		throws NoSuchNotificationLayoutConfigException, SystemException {
		NotificationLayoutConfig notificationLayoutConfig = findByNotificationConfigId(notificationConfigId,
				isEmployee, isPayment);

		return remove(notificationLayoutConfig);
	}

	/**
	 * Returns the number of notification layout configs where notificationConfigId = &#63; and isEmployee = &#63; and isPayment = &#63;.
	 *
	 * @param notificationConfigId the notification config ID
	 * @param isEmployee the is employee
	 * @param isPayment the is payment
	 * @return the number of matching notification layout configs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByNotificationConfigId(long notificationConfigId,
		boolean isEmployee, boolean isPayment) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NOTIFICATIONCONFIGID;

		Object[] finderArgs = new Object[] {
				notificationConfigId, isEmployee, isPayment
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_NOTIFICATIONLAYOUTCONFIG_WHERE);

			query.append(_FINDER_COLUMN_NOTIFICATIONCONFIGID_NOTIFICATIONCONFIGID_2);

			query.append(_FINDER_COLUMN_NOTIFICATIONCONFIGID_ISEMPLOYEE_2);

			query.append(_FINDER_COLUMN_NOTIFICATIONCONFIGID_ISPAYMENT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(notificationConfigId);

				qPos.add(isEmployee);

				qPos.add(isPayment);

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

	private static final String _FINDER_COLUMN_NOTIFICATIONCONFIGID_NOTIFICATIONCONFIGID_2 =
		"notificationLayoutConfig.notificationConfigId = ? AND ";
	private static final String _FINDER_COLUMN_NOTIFICATIONCONFIGID_ISEMPLOYEE_2 =
		"notificationLayoutConfig.isEmployee = ? AND ";
	private static final String _FINDER_COLUMN_NOTIFICATIONCONFIGID_ISPAYMENT_2 = "notificationLayoutConfig.isPayment = ?";

	public NotificationLayoutConfigPersistenceImpl() {
		setModelClass(NotificationLayoutConfig.class);
	}

	/**
	 * Caches the notification layout config in the entity cache if it is enabled.
	 *
	 * @param notificationLayoutConfig the notification layout config
	 */
	@Override
	public void cacheResult(NotificationLayoutConfig notificationLayoutConfig) {
		EntityCacheUtil.putResult(NotificationLayoutConfigModelImpl.ENTITY_CACHE_ENABLED,
			NotificationLayoutConfigImpl.class,
			notificationLayoutConfig.getPrimaryKey(), notificationLayoutConfig);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NOTIFICATIONCONFIGID,
			new Object[] {
				notificationLayoutConfig.getNotificationConfigId(),
				notificationLayoutConfig.getIsEmployee(),
				notificationLayoutConfig.getIsPayment()
			}, notificationLayoutConfig);

		notificationLayoutConfig.resetOriginalValues();
	}

	/**
	 * Caches the notification layout configs in the entity cache if it is enabled.
	 *
	 * @param notificationLayoutConfigs the notification layout configs
	 */
	@Override
	public void cacheResult(
		List<NotificationLayoutConfig> notificationLayoutConfigs) {
		for (NotificationLayoutConfig notificationLayoutConfig : notificationLayoutConfigs) {
			if (EntityCacheUtil.getResult(
						NotificationLayoutConfigModelImpl.ENTITY_CACHE_ENABLED,
						NotificationLayoutConfigImpl.class,
						notificationLayoutConfig.getPrimaryKey()) == null) {
				cacheResult(notificationLayoutConfig);
			}
			else {
				notificationLayoutConfig.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all notification layout configs.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(NotificationLayoutConfigImpl.class.getName());
		}

		EntityCacheUtil.clearCache(NotificationLayoutConfigImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the notification layout config.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NotificationLayoutConfig notificationLayoutConfig) {
		EntityCacheUtil.removeResult(NotificationLayoutConfigModelImpl.ENTITY_CACHE_ENABLED,
			NotificationLayoutConfigImpl.class,
			notificationLayoutConfig.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(notificationLayoutConfig);
	}

	@Override
	public void clearCache(
		List<NotificationLayoutConfig> notificationLayoutConfigs) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (NotificationLayoutConfig notificationLayoutConfig : notificationLayoutConfigs) {
			EntityCacheUtil.removeResult(NotificationLayoutConfigModelImpl.ENTITY_CACHE_ENABLED,
				NotificationLayoutConfigImpl.class,
				notificationLayoutConfig.getPrimaryKey());

			clearUniqueFindersCache(notificationLayoutConfig);
		}
	}

	protected void cacheUniqueFindersCache(
		NotificationLayoutConfig notificationLayoutConfig) {
		if (notificationLayoutConfig.isNew()) {
			Object[] args = new Object[] {
					notificationLayoutConfig.getNotificationConfigId(),
					notificationLayoutConfig.getIsEmployee(),
					notificationLayoutConfig.getIsPayment()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NOTIFICATIONCONFIGID,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NOTIFICATIONCONFIGID,
				args, notificationLayoutConfig);
		}
		else {
			NotificationLayoutConfigModelImpl notificationLayoutConfigModelImpl = (NotificationLayoutConfigModelImpl)notificationLayoutConfig;

			if ((notificationLayoutConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_NOTIFICATIONCONFIGID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						notificationLayoutConfig.getNotificationConfigId(),
						notificationLayoutConfig.getIsEmployee(),
						notificationLayoutConfig.getIsPayment()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NOTIFICATIONCONFIGID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NOTIFICATIONCONFIGID,
					args, notificationLayoutConfig);
			}
		}
	}

	protected void clearUniqueFindersCache(
		NotificationLayoutConfig notificationLayoutConfig) {
		NotificationLayoutConfigModelImpl notificationLayoutConfigModelImpl = (NotificationLayoutConfigModelImpl)notificationLayoutConfig;

		Object[] args = new Object[] {
				notificationLayoutConfig.getNotificationConfigId(),
				notificationLayoutConfig.getIsEmployee(),
				notificationLayoutConfig.getIsPayment()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NOTIFICATIONCONFIGID,
			args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NOTIFICATIONCONFIGID,
			args);

		if ((notificationLayoutConfigModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_NOTIFICATIONCONFIGID.getColumnBitmask()) != 0) {
			args = new Object[] {
					notificationLayoutConfigModelImpl.getOriginalNotificationConfigId(),
					notificationLayoutConfigModelImpl.getOriginalIsEmployee(),
					notificationLayoutConfigModelImpl.getOriginalIsPayment()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NOTIFICATIONCONFIGID,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NOTIFICATIONCONFIGID,
				args);
		}
	}

	/**
	 * Creates a new notification layout config with the primary key. Does not add the notification layout config to the database.
	 *
	 * @param notificationLayoutConfigId the primary key for the new notification layout config
	 * @return the new notification layout config
	 */
	@Override
	public NotificationLayoutConfig create(long notificationLayoutConfigId) {
		NotificationLayoutConfig notificationLayoutConfig = new NotificationLayoutConfigImpl();

		notificationLayoutConfig.setNew(true);
		notificationLayoutConfig.setPrimaryKey(notificationLayoutConfigId);

		return notificationLayoutConfig;
	}

	/**
	 * Removes the notification layout config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param notificationLayoutConfigId the primary key of the notification layout config
	 * @return the notification layout config that was removed
	 * @throws org.opencps.notificationmgt.NoSuchNotificationLayoutConfigException if a notification layout config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotificationLayoutConfig remove(long notificationLayoutConfigId)
		throws NoSuchNotificationLayoutConfigException, SystemException {
		return remove((Serializable)notificationLayoutConfigId);
	}

	/**
	 * Removes the notification layout config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the notification layout config
	 * @return the notification layout config that was removed
	 * @throws org.opencps.notificationmgt.NoSuchNotificationLayoutConfigException if a notification layout config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotificationLayoutConfig remove(Serializable primaryKey)
		throws NoSuchNotificationLayoutConfigException, SystemException {
		Session session = null;

		try {
			session = openSession();

			NotificationLayoutConfig notificationLayoutConfig = (NotificationLayoutConfig)session.get(NotificationLayoutConfigImpl.class,
					primaryKey);

			if (notificationLayoutConfig == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNotificationLayoutConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(notificationLayoutConfig);
		}
		catch (NoSuchNotificationLayoutConfigException nsee) {
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
	protected NotificationLayoutConfig removeImpl(
		NotificationLayoutConfig notificationLayoutConfig)
		throws SystemException {
		notificationLayoutConfig = toUnwrappedModel(notificationLayoutConfig);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(notificationLayoutConfig)) {
				notificationLayoutConfig = (NotificationLayoutConfig)session.get(NotificationLayoutConfigImpl.class,
						notificationLayoutConfig.getPrimaryKeyObj());
			}

			if (notificationLayoutConfig != null) {
				session.delete(notificationLayoutConfig);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (notificationLayoutConfig != null) {
			clearCache(notificationLayoutConfig);
		}

		return notificationLayoutConfig;
	}

	@Override
	public NotificationLayoutConfig updateImpl(
		org.opencps.notificationmgt.model.NotificationLayoutConfig notificationLayoutConfig)
		throws SystemException {
		notificationLayoutConfig = toUnwrappedModel(notificationLayoutConfig);

		boolean isNew = notificationLayoutConfig.isNew();

		NotificationLayoutConfigModelImpl notificationLayoutConfigModelImpl = (NotificationLayoutConfigModelImpl)notificationLayoutConfig;

		Session session = null;

		try {
			session = openSession();

			if (notificationLayoutConfig.isNew()) {
				session.save(notificationLayoutConfig);

				notificationLayoutConfig.setNew(false);
			}
			else {
				session.merge(notificationLayoutConfig);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !NotificationLayoutConfigModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((notificationLayoutConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_N_D_E.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						notificationLayoutConfigModelImpl.getOriginalNotificationConfigId(),
						notificationLayoutConfigModelImpl.getOriginalDictItemId(),
						notificationLayoutConfigModelImpl.getOriginalIsEmployee()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_N_D_E, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_N_D_E,
					args);

				args = new Object[] {
						notificationLayoutConfigModelImpl.getNotificationConfigId(),
						notificationLayoutConfigModelImpl.getDictItemId(),
						notificationLayoutConfigModelImpl.getIsEmployee()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_N_D_E, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_N_D_E,
					args);
			}
		}

		EntityCacheUtil.putResult(NotificationLayoutConfigModelImpl.ENTITY_CACHE_ENABLED,
			NotificationLayoutConfigImpl.class,
			notificationLayoutConfig.getPrimaryKey(), notificationLayoutConfig);

		clearUniqueFindersCache(notificationLayoutConfig);
		cacheUniqueFindersCache(notificationLayoutConfig);

		return notificationLayoutConfig;
	}

	protected NotificationLayoutConfig toUnwrappedModel(
		NotificationLayoutConfig notificationLayoutConfig) {
		if (notificationLayoutConfig instanceof NotificationLayoutConfigImpl) {
			return notificationLayoutConfig;
		}

		NotificationLayoutConfigImpl notificationLayoutConfigImpl = new NotificationLayoutConfigImpl();

		notificationLayoutConfigImpl.setNew(notificationLayoutConfig.isNew());
		notificationLayoutConfigImpl.setPrimaryKey(notificationLayoutConfig.getPrimaryKey());

		notificationLayoutConfigImpl.setNotificationLayoutConfigId(notificationLayoutConfig.getNotificationLayoutConfigId());
		notificationLayoutConfigImpl.setCompanyId(notificationLayoutConfig.getCompanyId());
		notificationLayoutConfigImpl.setGroupId(notificationLayoutConfig.getGroupId());
		notificationLayoutConfigImpl.setUserId(notificationLayoutConfig.getUserId());
		notificationLayoutConfigImpl.setCreateDate(notificationLayoutConfig.getCreateDate());
		notificationLayoutConfigImpl.setModifiedDate(notificationLayoutConfig.getModifiedDate());
		notificationLayoutConfigImpl.setNotificationConfigId(notificationLayoutConfig.getNotificationConfigId());
		notificationLayoutConfigImpl.setDictItemId(notificationLayoutConfig.getDictItemId());
		notificationLayoutConfigImpl.setPlId(notificationLayoutConfig.getPlId());
		notificationLayoutConfigImpl.setUserGroupName(notificationLayoutConfig.getUserGroupName());
		notificationLayoutConfigImpl.setPattern(notificationLayoutConfig.getPattern());
		notificationLayoutConfigImpl.setEventName(notificationLayoutConfig.getEventName());
		notificationLayoutConfigImpl.setDescription(notificationLayoutConfig.getDescription());
		notificationLayoutConfigImpl.setIsEmployee(notificationLayoutConfig.isIsEmployee());
		notificationLayoutConfigImpl.setIsPayment(notificationLayoutConfig.isIsPayment());

		return notificationLayoutConfigImpl;
	}

	/**
	 * Returns the notification layout config with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the notification layout config
	 * @return the notification layout config
	 * @throws org.opencps.notificationmgt.NoSuchNotificationLayoutConfigException if a notification layout config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotificationLayoutConfig findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNotificationLayoutConfigException, SystemException {
		NotificationLayoutConfig notificationLayoutConfig = fetchByPrimaryKey(primaryKey);

		if (notificationLayoutConfig == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNotificationLayoutConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return notificationLayoutConfig;
	}

	/**
	 * Returns the notification layout config with the primary key or throws a {@link org.opencps.notificationmgt.NoSuchNotificationLayoutConfigException} if it could not be found.
	 *
	 * @param notificationLayoutConfigId the primary key of the notification layout config
	 * @return the notification layout config
	 * @throws org.opencps.notificationmgt.NoSuchNotificationLayoutConfigException if a notification layout config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotificationLayoutConfig findByPrimaryKey(
		long notificationLayoutConfigId)
		throws NoSuchNotificationLayoutConfigException, SystemException {
		return findByPrimaryKey((Serializable)notificationLayoutConfigId);
	}

	/**
	 * Returns the notification layout config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the notification layout config
	 * @return the notification layout config, or <code>null</code> if a notification layout config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotificationLayoutConfig fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		NotificationLayoutConfig notificationLayoutConfig = (NotificationLayoutConfig)EntityCacheUtil.getResult(NotificationLayoutConfigModelImpl.ENTITY_CACHE_ENABLED,
				NotificationLayoutConfigImpl.class, primaryKey);

		if (notificationLayoutConfig == _nullNotificationLayoutConfig) {
			return null;
		}

		if (notificationLayoutConfig == null) {
			Session session = null;

			try {
				session = openSession();

				notificationLayoutConfig = (NotificationLayoutConfig)session.get(NotificationLayoutConfigImpl.class,
						primaryKey);

				if (notificationLayoutConfig != null) {
					cacheResult(notificationLayoutConfig);
				}
				else {
					EntityCacheUtil.putResult(NotificationLayoutConfigModelImpl.ENTITY_CACHE_ENABLED,
						NotificationLayoutConfigImpl.class, primaryKey,
						_nullNotificationLayoutConfig);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(NotificationLayoutConfigModelImpl.ENTITY_CACHE_ENABLED,
					NotificationLayoutConfigImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return notificationLayoutConfig;
	}

	/**
	 * Returns the notification layout config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param notificationLayoutConfigId the primary key of the notification layout config
	 * @return the notification layout config, or <code>null</code> if a notification layout config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotificationLayoutConfig fetchByPrimaryKey(
		long notificationLayoutConfigId) throws SystemException {
		return fetchByPrimaryKey((Serializable)notificationLayoutConfigId);
	}

	/**
	 * Returns all the notification layout configs.
	 *
	 * @return the notification layout configs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<NotificationLayoutConfig> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notification layout configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.notificationmgt.model.impl.NotificationLayoutConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of notification layout configs
	 * @param end the upper bound of the range of notification layout configs (not inclusive)
	 * @return the range of notification layout configs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<NotificationLayoutConfig> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the notification layout configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.notificationmgt.model.impl.NotificationLayoutConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of notification layout configs
	 * @param end the upper bound of the range of notification layout configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of notification layout configs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<NotificationLayoutConfig> findAll(int start, int end,
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

		List<NotificationLayoutConfig> list = (List<NotificationLayoutConfig>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_NOTIFICATIONLAYOUTCONFIG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NOTIFICATIONLAYOUTCONFIG;

				if (pagination) {
					sql = sql.concat(NotificationLayoutConfigModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<NotificationLayoutConfig>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<NotificationLayoutConfig>(list);
				}
				else {
					list = (List<NotificationLayoutConfig>)QueryUtil.list(q,
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
	 * Removes all the notification layout configs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (NotificationLayoutConfig notificationLayoutConfig : findAll()) {
			remove(notificationLayoutConfig);
		}
	}

	/**
	 * Returns the number of notification layout configs.
	 *
	 * @return the number of notification layout configs
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

				Query q = session.createQuery(_SQL_COUNT_NOTIFICATIONLAYOUTCONFIG);

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
	 * Initializes the notification layout config persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.opencps.notificationmgt.model.NotificationLayoutConfig")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<NotificationLayoutConfig>> listenersList = new ArrayList<ModelListener<NotificationLayoutConfig>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<NotificationLayoutConfig>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(NotificationLayoutConfigImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_NOTIFICATIONLAYOUTCONFIG = "SELECT notificationLayoutConfig FROM NotificationLayoutConfig notificationLayoutConfig";
	private static final String _SQL_SELECT_NOTIFICATIONLAYOUTCONFIG_WHERE = "SELECT notificationLayoutConfig FROM NotificationLayoutConfig notificationLayoutConfig WHERE ";
	private static final String _SQL_COUNT_NOTIFICATIONLAYOUTCONFIG = "SELECT COUNT(notificationLayoutConfig) FROM NotificationLayoutConfig notificationLayoutConfig";
	private static final String _SQL_COUNT_NOTIFICATIONLAYOUTCONFIG_WHERE = "SELECT COUNT(notificationLayoutConfig) FROM NotificationLayoutConfig notificationLayoutConfig WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "notificationLayoutConfig.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NotificationLayoutConfig exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No NotificationLayoutConfig exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(NotificationLayoutConfigPersistenceImpl.class);
	private static NotificationLayoutConfig _nullNotificationLayoutConfig = new NotificationLayoutConfigImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<NotificationLayoutConfig> toCacheModel() {
				return _nullNotificationLayoutConfigCacheModel;
			}
		};

	private static CacheModel<NotificationLayoutConfig> _nullNotificationLayoutConfigCacheModel =
		new CacheModel<NotificationLayoutConfig>() {
			@Override
			public NotificationLayoutConfig toEntityModel() {
				return _nullNotificationLayoutConfig;
			}
		};
}