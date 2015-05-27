package com.usp.kiss.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.usp.kiss.client.event.AggScoreChangedEvent;
import com.usp.kiss.client.event.AggScoreChangedEventHandler;

public class ExpectedScoreBoardView extends DialogBox {

    private static ExpectedScoreBoardViewUiBinder uiBinder = GWT
            .create(ExpectedScoreBoardViewUiBinder.class);

    interface ExpectedScoreBoardViewUiBinder extends
            UiBinder<Widget, ExpectedScoreBoardView> {
    }

    @UiField VerticalPanel containerView;
    @UiField HTML descLabel;
    @UiField HTML headerLabel;
    
    public ExpectedScoreBoardView() {
        setWidget(uiBinder.createAndBindUi(this));
        getElement().getStyle().setZIndex(999);
        setModal(true);
        setAutoHideEnabled(true);
        setGlassEnabled(true);
        setText("Press Esc to exit...");
    }
    
    @Override
    protected void onPreviewNativeEvent(NativePreviewEvent event) {
        super.onPreviewNativeEvent(event);
        switch (event.getTypeInt()) {
            case Event.ONKEYDOWN:
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
                    hide();
                }
                break;
        }
    }
    
    public void setExpected(int[] expected, String episodeTitle, int episodeId) {
        containerView.clear();
        int cardCount = AppUtils.getCardCount(episodeTitle);
        
        String header = "<h2>" + cardCount
                + " "
                + AppUtils.getSuit(episodeTitle).getValue() + "</h2>";
        headerLabel.setHTML(header);
        
        FlexTable table = new FlexTable();
        table.setWidth("100%");
        table.setBorderWidth(0);
        table.setCellPadding(5);
        HTMLTable.RowFormatter rf = table.getRowFormatter();
        int total = 0;
        int maxScore = AppUtils.getMaxScore();
        
        HTML trail = new HTML();
        trail.setHTML("<div align=\"center\"><h2>Trail by</h2></div>");
        table.setWidget(0, 2, trail);
        
        for (int i = 0; i < expected.length; i++) {
            int index = (episodeId + i + 1) % AppUtils.getPlayerNames().size();
            total += expected[index];
            HTML name = new HTML();
            name.setHTML("<div style=\"margin-left:15%;\"><h2>" + AppUtils.getPlayerNames().get(index) + " </h2></div>");
            table.setWidget(i + 1, 0, name);
            
            HTML value = new HTML();
            value.setHTML("<div align=\"center\"><h2>" + expected[index] + "</h2></div>");
            table.setWidget(i + 1, 1, value);
            
            
            HTML behind = new HTML();
            behind.setHTML("<div align=\"center\"><h2>"
               + (maxScore - AppUtils.getAggScores()[index])
               + "</h2></div>");
            table.setWidget(i + 1, 2, behind);
            
            if (i % 2 == 1) {
                rf.addStyleName(i + 1, "FlexTable-OddRow");
            } else {
                rf.addStyleName(i + 1, "FlexTable-EvenRow");
            }
        }
        HTML totalView = new HTML();
        totalView.setHTML("<div style=\"margin-left:15%;color:blue;\"><h2> Total </h2></div>");
        table.setWidget(expected.length + 1, 0, totalView);
        
        HTML totalCountView = new HTML();
        totalCountView.setHTML("<div style=\"color:blue;\" align=\"center\"><h2>"+ total + " </h2></div>");
        table.setWidget(expected.length + 1, 1, totalCountView);
        
        containerView.add(table);
        if (total < cardCount) {
            descLabel.setHTML("Under expected by : " + (cardCount - total));
        }
        if (total > cardCount) {
            descLabel.setHTML("Over expected by : " + (total - cardCount));
        }
        if (total == cardCount) {
            descLabel.setHTML("Card count and expectation count is same");
        }
    }
}
