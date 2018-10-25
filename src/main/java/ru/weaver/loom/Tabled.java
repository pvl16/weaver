package ru.weaver.loom;

import java.awt.*;
import java.util.ArrayList;

public class Tabled extends Pattern {
//    private int colorCnt = 0;
//    private ArrayList<Color> colors = null;
    enum ActionDirect{Forward, Back, Intermission}

    private class Card {
        private short HoleCnt;
        private ArrayList<Color> colors = null;

        public Card(short holeCnt) {
            HoleCnt = holeCnt;
            colors = new ArrayList<Color>();
            for (short i = 0; i < HoleCnt; i++)
                colors.add(Color.RED);
        }
    }

    private short cardCnt = 0;
    private ArrayList<Card> cards = null;

    private class Action {
        short cardNum;
        ActionDirect direct;
        short distance;

        public Action(short cardNum, ActionDirect direct, short distance) {
            this.cardNum = cardNum;
            this.direct = direct;
            this.distance = distance;
        }
    }

    private class Pick {
        private ArrayList<Action> actions = null;

        public Pick() {
            actions = new ArrayList<Action>();
            for (short i = 0; i < cardCnt; i++) {
                actions.add(new Action(i, ActionDirect.Forward, (short) 1));
            }
        }
    }

    private int pickCnt = 0;
    private ArrayList<Pick> picks = null;

    public Tabled(short CardCnt, int PickCnt, short HoleCntDef) {
        cardCnt = CardCnt;
        pickCnt = PickCnt;

        cards = new ArrayList<Card>();
        for (short i = 0; i< cardCnt ; i++) {
            cards.add(new Card(HoleCntDef));
        }

        picks = new ArrayList<Pick>();
        for (short i = 0; i< pickCnt ; i++) {
            picks.add(new Pick());
        }

    }

    //    public Tabled(String filePath) {
//
//    }
}
