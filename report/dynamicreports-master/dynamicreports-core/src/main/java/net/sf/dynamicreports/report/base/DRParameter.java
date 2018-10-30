/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2012 Ricardo Mariaca
 * http://dynamicreports.sourceforge.net
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.dynamicreports.report.base;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.DRIParameter;

import org.apache.commons.lang3.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRParameter<T> implements DRIParameter<T> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private String name;
	private Class<T> valueClass;
	private T value;

	@SuppressWarnings("unchecked")
	public DRParameter(String name, T value) {
		Validate.notEmpty(name, "name must not be empty");
		Validate.notNull(value, "value must not be null");
		this.name = name;
		this.valueClass = (Class<T>) value.getClass();
		this.value = value;
	}

	public DRParameter(String name, Class<T> valueClass) {
		Validate.notEmpty(name, "name must not be empty");
		Validate.notNull(valueClass, "valueClass must not be null");
		this.name = name;
		this.valueClass = valueClass;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Class<T> getValueClass() {
		return valueClass;
	}

	@Override
	public T getValue() {
		return value;
	}
}
