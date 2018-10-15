package ru.weaver.loom;

import java.awt.*;
import java.util.ArrayList;

public class Sample extends Pattern {
    /**
     * cntTreadles - число педалей
     * cntHeddles  - число ремиз
     * cntWarps    - число нитей основы
     * cntWefts    - число нитей утка
     */
    private int cntTreadles;
    private int cntHeddles;
    private int cntWarps;
    private int cntWefts;
    private boolean isRoll;
    private ArrayList<Object> samples;

    private class Warp {
        private int numberHeddle = 0;
        Color color = null;

        public Warp(int numberHeddle, Color color) {
            this.numberHeddle = numberHeddle;
            this.color = color;
        }

        public int getNumberHeddle() {
            return numberHeddle;
        }

        public void setNumberHeddle(int numberHeddle) {
            if (numberHeddle < 0)
                numberHeddle = 0;
            if (numberHeddle >= cntHeddles)
                numberHeddle = cntHeddles - 1;
            this.numberHeddle = numberHeddle;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }

    private class Weft {
        private int numberTreadle = 0;
        Color color = null;

        public Weft(int numberTreadle, Color color) {
            this.numberTreadle = numberTreadle;
            this.color = color;
        }

        public int getNumberTreadle() {
            return numberTreadle;
        }

        public void setNumberTreadle(int numberTreadle) {
            if (numberTreadle < 0)
                numberTreadle = 0;
            if (numberTreadle >= cntTreadles)
                numberTreadle = cntTreadles - 1;
            this.numberTreadle = numberTreadle;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }

    private ArrayList<Warp> Warps = null;
    private ArrayList<Weft> Wefts = null;

    public Sample(int cntTreadles, int cntHeddles, int cntWarps, int cntWefts, boolean isRoll, Color clrWarps, Color clrWefts) {
      super();
      if (cntTreadles <= 0)
            throw new IllegalArgumentException("cntTreadles is negative");
        if (cntHeddles <= 0)
            throw new IllegalArgumentException("cntHeddles is negative");
        if ((isRoll)&&((cntHeddles % 2) == 1))
            throw new IllegalArgumentException("cntHeddles is not even");
        if (cntWarps <= 0)
            throw new IllegalArgumentException("cntWarps is negative");
        if (cntWefts <= 0)
            throw new IllegalArgumentException("cntWefts is negative");

        this.cntTreadles = cntTreadles;
        this.cntHeddles = cntHeddles;
        this.cntWarps = cntWarps;
        this.cntWefts = cntWefts;
        this.isRoll = isRoll;

        int tIndex = 0;

        Warps = new ArrayList<Warp>();
        for (int i = 0; i < cntWarps; i++) {
            Warps.add(new Warp(tIndex, clrWarps));
            tIndex++;
            if (tIndex >= cntHeddles)
                tIndex = 0;
        }

        Wefts = new ArrayList<Weft>();
        tIndex = 0;
        for (int i = 0; i < cntWefts; i++) {
            Wefts.add(new Weft(tIndex, clrWefts));
            tIndex++;
            if (tIndex >= cntTreadles)
                tIndex = 0;
        }

        samples = new ArrayList<Object>();
        for (int i = 0; i< cntHeddles; i++) {
            ArrayList<Boolean> o = new ArrayList<Boolean>();
            for (int j = 0; j< cntTreadles ; j++)
              o.add(i == j);
            samples.add(o);
        }

    }

    public int getCntTreadles() {
        return cntTreadles;
    }

    public void setCntTreadles(int newCntTreadles) throws Exception {
        if (newCntTreadles <= 0)
            throw new IllegalArgumentException("newCntTreadles is negative");
        if (cntTreadles == newCntTreadles)
            return;
        for (int i = 0 ; i < cntHeddles ; i++ ) {
            ArrayList<Boolean> o = (ArrayList<Boolean>)samples.get(i);
            if (cntTreadles > newCntTreadles) {
                for (int j = cntTreadles - 1; j >= newCntTreadles; j-- ) {
                    o.remove(j);
                }
            } else {
                int k = ((i % 2) == 0) ? i + 1 : i - 1;
                for (int j = cntTreadles; j < newCntTreadles; j++) {
                    if ((isRoll)&&(k < i)) {
                        o.add(!getIsUp(k, j));
                    } else {
                        o.add(i == j);
                    }
                }
            }
        }
        if (cntTreadles > newCntTreadles) {
            for (int i = 0; i < cntWefts; i++) {
                Weft w = Wefts.get(i);
                if (w.numberTreadle >= newCntTreadles) {
                    w.numberTreadle = 0;
                }
            }
        }
        this.cntTreadles = newCntTreadles;
    }

    public int getCntHeddles() {
        return cntHeddles;
    }

    public void setCntHeddles(int newCntHeddles) throws Exception {
        if (newCntHeddles <= 0)
            throw new IllegalArgumentException("newCntHeddles is negative");
        if ((isRoll)&&((newCntHeddles % 2) == 1))
            throw new IllegalArgumentException("newCntHeddles is not even");

        if (cntHeddles > newCntHeddles) {
            for (int i = cntHeddles - 1 ; i >= newCntHeddles ; i-- ) {
                samples.remove(i);
            }
            for (int i = 0; i < cntWarps; i++) {
                Warp w = Warps.get(i);
                if (w.numberHeddle >= newCntHeddles) {
                    w.numberHeddle = 0;
                }
            }
        } else {
            for (int i = cntHeddles ; i < newCntHeddles ; i++ ) {
                ArrayList<Boolean> o = (ArrayList<Boolean>)samples.get(i);
                int k = ((i % 2) == 0) ? i + 1 : i - 1;
                for (int j = 0; j < cntTreadles; j++) {
                    if ((isRoll)&&(k < i)) {
                        o.add(!getIsUp(k, j));
                    } else {
                        o.add(i == j);
                    }
                }
                samples.add(o);
            }
        }
        this.cntHeddles = newCntHeddles;
    }

    public int getCntWarps() {
        return cntWarps;
    }

    public void setCntWarps(int newCntWarps) throws Exception {
        if (newCntWarps <= 0)
            throw new IllegalArgumentException("newCntWarps is negative");
        if (cntWarps > newCntWarps) {
            for (int i = cntWarps - 1 ; i >= newCntWarps ; i-- ) {
                Warps.remove(i);
            }
        } else {
            int tIndex = Warps.get(cntWarps - 1).numberHeddle + 1;
            Color cl = Warps.get(cntWarps - 1).color;
            for (int i = cntWarps; i < newCntWarps; i++) {
                if (tIndex >= cntHeddles)
                    tIndex = 0;
                Warps.add(new Warp(tIndex, cl));
                tIndex++;
            }
        }
        this.cntWarps = newCntWarps;
    }

    public int getCntWefts() {
        return cntWefts;
    }

    public void setCntWefts(int newCntWefts) {
        if (newCntWefts <= 0)
            throw new IllegalArgumentException("newCntWarps is negative");
        if (cntWefts > newCntWefts) {
            for (int i = cntWefts - 1 ; i >= newCntWefts ; i-- ) {
                Wefts.remove(i);
            }
        } else {
            int tIndex = Wefts.get(cntWefts - 1).numberTreadle + 1;
            Color cl = Wefts.get(cntWefts - 1).color;
            for (int i = cntWefts; i < newCntWefts; i++) {
                if (tIndex >= cntTreadles)
                    tIndex = 0;
                Wefts.add(new Weft(tIndex, cl));
                tIndex++;
            }
        }
        this.cntWefts = newCntWefts;
    }

    public Color getColorWarp(int Index) {
        if ((Index < 0)||(Index >= cntWarps))
            throw new IndexOutOfBoundsException("Index is out Of bounds");
        return Warps.get(Index).color;
    }

    public void setColorWarp(int Index, Color cl) {
        if ((Index < 0)||(Index >= cntWarps))
            throw new IndexOutOfBoundsException("Index is out Of bounds");
        Warps.get(Index).setColor(cl);
    }

    public int getHeddleWarp(int Index) {
        if ((Index < 0)||(Index >= cntWarps))
            throw new IndexOutOfBoundsException("Index is out Of bounds");
        return Warps.get(Index).numberHeddle;
    }

    public void setHeddleWarp(int Index, int Heddle) {
        if ((Index < 0)||(Index >= cntWarps))
            throw new IndexOutOfBoundsException("Index is out Of bounds");
        if ((Heddle < 0)||(Heddle >= cntHeddles))
            throw new IndexOutOfBoundsException("Index is out Of bounds");
        Warps.get(Index).setNumberHeddle(Heddle);
    }

    public Color getColorWeft(int Index) {
        if ((Index < 0)||(Index >= cntWefts))
            throw new IndexOutOfBoundsException("Index is out Of bounds");
        return Wefts.get(Index).color;
    }

    public void setColorWeft(int Index, Color cl) {
        if ((Index < 0)||(Index >= cntWefts))
            throw new IndexOutOfBoundsException("Index is out Of bounds");
        Wefts.get(Index).setColor(cl);
    }

    public int getTreadleWeft(int Index) {
        if ((Index < 0)||(Index >= cntWefts))
            throw new IndexOutOfBoundsException("Index is out Of bounds");
        return Wefts.get(Index).numberTreadle;
    }

    public void setTreadleWeft(int Index, int Treadle) {
        if ((Index < 0)||(Index >= cntWefts))
            throw new IndexOutOfBoundsException("Index is out Of bounds");
        if ((Treadle < 0)||(Treadle >= cntTreadles))
            throw new IndexOutOfBoundsException("Treadle is out Of bounds");
        Wefts.get(Index).setNumberTreadle(Treadle);
    }

    public Boolean getIsSampleUp(int IndexHeddle, int IndexTreadle) throws Exception {
        return ((ArrayList<Boolean>) samples.get(IndexHeddle)).get(IndexTreadle);
    }

    public void setIsSampleUp(int IndexHeddle, int IndexTreadle, boolean isUp) throws Exception {
        ArrayList<Boolean> o = (ArrayList<Boolean>) samples.get(IndexHeddle);
        o.set(IndexTreadle, isUp);
    }

    public Boolean getIsUp(int IndexWarp, int IndexWeft) throws Exception {
        return getIsSampleUp(getHeddleWarp(IndexWarp), getTreadleWeft(IndexWeft));
    }

    public Color getColor(int IndexWarp, int IndexWeft) throws Exception {
        Boolean up = getIsUp(IndexWarp, IndexWeft);
        Color res = null;
        if (up) {
            res = getColorWarp(IndexWarp);
        } else {
            res = getColorWeft(IndexWeft);
        }
        return res;
    }

//    @Override
//    public void savetoFile(String path) {
//
//    }

}
