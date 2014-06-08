/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package com.dabi.habitv.tray.controller.todl;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * A class containing a {@link TreeCell} implementation that draws a
 * {@link CheckBox} node inside the cell, along with support for common
 * interactions (discussed in more depth shortly).
 * 
 * <p>
 * To make creating TreeViews with CheckBoxes easier, a convenience class called
 * {@link CheckBoxTreeItem} is provided. It is <b>highly</b> recommended that
 * developers use this class, rather than the regular {@link TreeItem} class,
 * when constructing their TreeView tree structures. Refer to the
 * CheckBoxTreeItem API documentation for an example on how these two classes
 * can be combined.
 * 
 * <p>
 * When used in a TreeView, the CheckBoxCell is rendered with a CheckBox to the
 * right of the 'disclosure node' (i.e. the arrow). The item stored in
 * {@link CheckBoxTreeItem#getValue()} will then have the StringConverter called
 * on it, and this text will take all remaining horizontal space. Additionally,
 * by using {@link CheckBoxTreeItem}, the TreeView will automatically handle
 * situations such as:
 * 
 * <ul>
 * <li>Clicking on the {@link CheckBox} beside an item that has children will
 * result in all children also becoming selected/unselected.
 * <li>Clicking on the {@link CheckBox} beside an item that has a parent will
 * possibly toggle the state of the parent. For example, if you select a single
 * child, the parent will become indeterminate (indicating partial selection of
 * children). If you proceed to select all children, the parent will then show
 * that it too is selected. This is recursive, with all parent nodes updating as
 * expected.
 * </ul>
 * 
 * If it is decided that using {@link CheckBoxTreeItem} is not desirable, then
 * it is necessary to call one of the constructors where a {@link Callback} is
 * provided that can return an {@code ObservableValue<Boolean>} given a
 * {@link TreeItem} instance. This {@code ObservableValue<Boolean>} should
 * represent the boolean state of the given {@link TreeItem}.
 * 
 * @param <T>
 *            The type of the elements contained within the TreeView TreeItem
 *            instances.
 * @since 2.2
 */
public abstract class MyCheckBoxTreeCell<T> extends TreeCell<T> {

	/***************************************************************************
	 * * Static cell factories * *
	 **************************************************************************/

	/***************************************************************************
	 * * Fields * *
	 **************************************************************************/
	private final CheckBox checkBox;

	private ObservableValue<Boolean> booleanProperty;

	private BooleanProperty indeterminateProperty;

	/***************************************************************************
	 * * Constructors * *
	 **************************************************************************/

	/**
	 * Creates a default {@link MyCheckBoxTreeCell} that assumes the TreeView is
	 * constructed with {@link CheckBoxTreeItem} instances, rather than the
	 * default {@link TreeItem}. By using {@link CheckBoxTreeItem}, it will
	 * internally manage the selected and indeterminate state of each item in
	 * the tree.
	 * @param strConverter 
	 */
	public MyCheckBoxTreeCell(StringConverter<TreeItem<T>> strConverter) {
		// getSelectedProperty as anonymous inner class to deal with situation
		// where the user is using CheckBoxTreeItem instances in their tree
		this(new Callback<TreeItem<T>, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(TreeItem<T> item) {
				if (item instanceof CheckBoxTreeItem<?>) {
					return ((CheckBoxTreeItem<?>) item).selectedProperty();
				}
				return null;
			}
		}, strConverter);
	}

	/**
	 * Creates a {@link MyCheckBoxTreeCell} for use in a TreeView control via a
	 * cell factory. Unlike {@link MyCheckBoxTreeCell#CheckBoxTreeCell()}, this
	 * method does not assume that all TreeItem instances in the TreeView are
	 * {@link CheckBoxTreeItem}.
	 * 
	 * <p>
	 * To call this method, it is necessary to provide a {@link Callback} that,
	 * given an object of type TreeItem<T>, will return an
	 * {@code ObservableValue<Boolean>} that represents whether the given item
	 * is selected or not. This {@code ObservableValue<Boolean>} will be bound
	 * bidirectionally (meaning that the CheckBox in the cell will set/unset
	 * this property based on user interactions, and the CheckBox will reflect
	 * the state of the {@code ObservableValue<Boolean>}, if it changes
	 * externally).
	 * 
	 * <p>
	 * If the items are not {@link CheckBoxTreeItem} instances, it becomes the
	 * developers responsibility to handle updating the state of parent and
	 * children TreeItems. This means that, given a TreeItem, this class will
	 * simply toggles the {@code ObservableValue<Boolean>} that is provided, and
	 * no more. Of course, this functionality can then be implemented externally
	 * by adding observers to the {@code ObservableValue<Boolean>}, and toggling
	 * the state of other properties as necessary.
	 * 
	 * @param getSelectedProperty
	 *            A {@link Callback} that will return an
	 *            {@code ObservableValue<Boolean>} that represents whether the
	 *            given item is selected or not.
	 */
	private final static StringConverter defaultTreeItemStringConverter = new StringConverter<TreeItem>() {
		@Override
		public String toString(TreeItem treeItem) {
			return (treeItem == null || treeItem.getValue() == null) ? ""
					: treeItem.getValue().toString();
		}

		@Override
		public TreeItem fromString(String string) {
			return new TreeItem(string);
		}
	};

	public MyCheckBoxTreeCell(
			final Callback<TreeItem<T>, ObservableValue<Boolean>> getSelectedProperty) {
		this(getSelectedProperty, defaultTreeItemStringConverter);
	}

	public MyCheckBoxTreeCell(
			final Callback<TreeItem<T>, ObservableValue<Boolean>> getSelectedProperty,
			final StringConverter<TreeItem<T>> converter) {
		if (getSelectedProperty == null) {
			throw new NullPointerException(
					"getSelectedProperty can not be null");
		}
		this.getStyleClass().add("choice-box-tree-cell");
		setSelectedStateCallback(getSelectedProperty);
		setConverter(converter);

		this.checkBox = new CheckBox();
		this.checkBox.setAllowIndeterminate(false);
		setGraphic(checkBox);
	}

	/***************************************************************************
	 * * Properties * *
	 **************************************************************************/

	// --- converter
	private ObjectProperty<StringConverter<TreeItem<T>>> converter = new SimpleObjectProperty<StringConverter<TreeItem<T>>>(
			this, "converter");

	/**
	 * The {@link StringConverter} property.
	 */
	public final ObjectProperty<StringConverter<TreeItem<T>>> converterProperty() {
		return converter;
	}

	/**
	 * Sets the {@link StringConverter} to be used in this cell.
	 */
	public final void setConverter(StringConverter<TreeItem<T>> value) {
		converterProperty().set(value);
	}

	/**
	 * Returns the {@link StringConverter} used in this cell.
	 */
	public final StringConverter<TreeItem<T>> getConverter() {
		return converterProperty().get();
	}

	// --- selected state callback property
	private ObjectProperty<Callback<TreeItem<T>, ObservableValue<Boolean>>> selectedStateCallback = new SimpleObjectProperty<Callback<TreeItem<T>, ObservableValue<Boolean>>>(
			this, "selectedStateCallback");

	/**
	 * Property representing the {@link Callback} that is bound to by the
	 * CheckBox shown on screen.
	 */
	public final ObjectProperty<Callback<TreeItem<T>, ObservableValue<Boolean>>> selectedStateCallbackProperty() {
		return selectedStateCallback;
	}

	/**
	 * Sets the {@link Callback} that is bound to by the CheckBox shown on
	 * screen.
	 */
	public final void setSelectedStateCallback(
			Callback<TreeItem<T>, ObservableValue<Boolean>> value) {
		selectedStateCallbackProperty().set(value);
	}

	/**
	 * Returns the {@link Callback} that is bound to by the CheckBox shown on
	 * screen.
	 */
	public final Callback<TreeItem<T>, ObservableValue<Boolean>> getSelectedStateCallback() {
		return selectedStateCallbackProperty().get();
	}

	/***************************************************************************
	 * * Public API * *
	 **************************************************************************/

	/** {@inheritDoc} */
	@Override
	public void updateItem(T item, boolean empty) {
		super.updateItem(item, empty);

		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			StringConverter c = getConverter();
			Callback<TreeItem<T>, ObservableValue<Boolean>> callback = getSelectedStateCallback();

			// update the node content
			setText(c.toString(getTreeItem()));
			if (showCheckBox(item)) {
				setGraphic(checkBox);
			} else {
				setGraphic(new Label("     "));
			}

			// uninstall bindings
			if (booleanProperty != null) {
				checkBox.selectedProperty().unbindBidirectional(
						(BooleanProperty) booleanProperty);
			}
			if (indeterminateProperty != null) {
				checkBox.indeterminateProperty().unbindBidirectional(
						indeterminateProperty);
			}

			// install new bindings.
			// We special case things when the TreeItem is a CheckBoxTreeItem
			if (getTreeItem() instanceof CheckBoxTreeItem) {
				CheckBoxTreeItem<T> cbti = (CheckBoxTreeItem<T>) getTreeItem();
				booleanProperty = cbti.selectedProperty();
				checkBox.selectedProperty().bindBidirectional(
						(BooleanProperty) booleanProperty);

				indeterminateProperty = cbti.indeterminateProperty();
				checkBox.indeterminateProperty().bindBidirectional(
						indeterminateProperty);
			} else {
				booleanProperty = callback.call(getTreeItem());
				if (booleanProperty != null) {
					checkBox.selectedProperty().bindBidirectional(
							(BooleanProperty) booleanProperty);
				}
			}
		}
	}

	protected abstract boolean showCheckBox(T item);
}