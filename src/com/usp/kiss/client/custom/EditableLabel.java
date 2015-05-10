package com.usp.kiss.client.custom;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class EditableLabel extends TextBox {

    public EditableLabel(String valueOf) {
        super();
        if (!valueOf.equals("-1")) {
            setText(valueOf);
        }
        addStyleName("myLabelCss");
    }

    public EditableLabel() {
        this("");
    }

    @Override
    public void setText(String text) {
        if (!text.equals("-1")) {
            super.setText(text);
        } else {
            super.setText("");
        }

        if (getText().startsWith("-")) {
            getElement().getStyle().setBackgroundColor("rgb(13, 62, 219)");
        } else if(getText().equals("0")) {
            getElement().getStyle().setBackgroundColor("teal");
        }

        try {
            Integer.parseInt(getText());
        } catch (NumberFormatException e) {
            getElement().getStyle().setBackgroundColor("teal");
        }
    }

}

//public class EditableLabel extends Composite implements HasValue<String>, HasKeyUpHandlers {
//
//    private static EditableLabelUiBinder uiBinder = GWT
//            .create(EditableLabelUiBinder.class);
//
//    interface EditableLabelUiBinder extends UiBinder<Widget, EditableLabel> {
//    }
//
//    @UiField
//    protected Label editLabel;
//
//    @UiField
//    protected DeckPanel deckPanel;
//
//    @UiField
//    protected TextBox editBox;
//
//    @UiField
//    protected FocusPanel focusPanel;
//
//    public EditableLabel() {
//        this("");
//    }
//    
//    public EditableLabel(String text) {
//        initWidget(uiBinder.createAndBindUi(this));
//        getWidget().addStyleName("myLabelCss");
//
//        deckPanel.showWidget(0);
//        focusPanel.addFocusHandler(new FocusHandler() {
//            public void onFocus(FocusEvent event) {
//                switchToEdit();
//            }
//        });
//
//        editLabel.addClickHandler(new ClickHandler() {
//            public void onClick(ClickEvent event) {
//                switchToEdit();
//            }
//        });
//
//        editBox.addBlurHandler(new BlurHandler() {
//            public void onBlur(BlurEvent event) {
//                switchToLabel();
//            }
//        });
//        
//        editBox.addKeyPressHandler(new KeyPressHandler() {
//            public void onKeyPress(KeyPressEvent event) {
//
//                if (event.getCharCode() == KeyCodes.KEY_ENTER) {
//                    switchToLabel();
//                }
//                else if (event.getCharCode() == KeyCodes.KEY_ESCAPE) {
//                    editBox.setText(editLabel.getText()); // reset to the original value
//                }
//            }
//        });
//        setValue(text);
//    }
//
//    public void switchToEdit() {
//        if(deckPanel.getVisibleWidget() == 1) return;
//        editBox.setText(getValue());
//        deckPanel.showWidget(1);
//        editBox.setFocus(true);
//    }
//
//    public void switchToLabel() {
//        if (editBox.getText().isEmpty()) {
//            switchToEdit();
//            return;
//        }
//        if(deckPanel.getVisibleWidget() == 0) return;
//        setValue(editBox.getText(), true); // fires events, too
//        deckPanel.showWidget(0);
//    }
//
//    public HandlerRegistration addValueChangeHandler(
//            ValueChangeHandler<String> handler) {
//        return addHandler(handler, ValueChangeEvent.getType());
//    }
//
//    public String getValue() {
//        return editBox.getText();
//    }
//
//    public void setValue(String value) {
//        if (value.startsWith("-")) {
//            editLabel.setText("");
//            editBox.setText("");
//            switchToEdit();
//            return;
//        }
//
//        ValueChangeEvent.fireIfNotEqual(this, editLabel.getText(), value);
//        editLabel.setText(value);
//        editBox.setText(value);
//    }
//
//    public void setValue(String value, boolean fireEvents) {
//        if(fireEvents) ValueChangeEvent.fireIfNotEqual(this, getValue(), value);
//        setValue(value);
//    }
//
//    public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
//        return addDomHandler(handler, KeyUpEvent.getType());
//    }
//}