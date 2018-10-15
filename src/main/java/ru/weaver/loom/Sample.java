package ru.weaver.loom;

import ru.weaver.shemas.loom.*;

import java.awt.*;
import java.util.ArrayList;

public class Sample extends Pattern {
    /**
     * cntTreadles - число педалей
     * cntHeddles  - число ремиз
     * cntWarps    - число нитей основы
     * cntWefts    - число нитей утка
     */
    private short cntTreadles;
    private short cntHeddles;
    private short cntWarps;
    private short cntWefts;
    private boolean isRoll;
    private ArrayList<Object> samples;

    private class Warp {
        private short numberHeddle = 0;
        Color color = null;

        public Warp(short numberHeddle, Color color) {
            this.numberHeddle = numberHeddle;
            this.color = color;
        }

        public short getNumberHeddle() {
            return numberHeddle;
        }

        public void setNumberHeddle(short numberHeddle) {
            if (numberHeddle < 0)
                numberHeddle = 0;
            if (numberHeddle >= cntHeddles)
                numberHeddle = (short)(cntHeddles - 1);
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
        private short numberTreadle = 0;
        Color color = null;

        public Weft(short numberTreadle, Color color) {
            this.numberTreadle = numberTreadle;
            this.color = color;
        }

        public short getNumberTreadle() {
            return numberTreadle;
        }

        public void setNumberTreadle(short numberTreadle) {
            if (numberTreadle < 0)
                numberTreadle = 0;
            if (numberTreadle >= cntTreadles)
                numberTreadle = (short)(cntTreadles - 1);
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

    public Sample(short cntTreadles, short cntHeddles, short cntWarps, short cntWefts, boolean isRoll, Color clrWarps, Color clrWefts) {
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

        short tIndex = 0;

        Warps = new ArrayList<Warp>();
        for (short i = 0; i < cntWarps; i++) {
            Warps.add(new Warp(tIndex, clrWarps));
            tIndex++;
            if (tIndex >= cntHeddles)
                tIndex = 0;
        }

        Wefts = new ArrayList<Weft>();
        tIndex = 0;
        for (short i = 0; i < cntWefts; i++) {
            Wefts.add(new Weft(tIndex, clrWefts));
            tIndex++;
            if (tIndex >= cntTreadles)
                tIndex = 0;
        }

        samples = new ArrayList<Object>();
        for (short i = 0; i< cntHeddles; i++) {
            ArrayList<Boolean> o = new ArrayList<Boolean>();
            for (short j = 0; j< cntTreadles ; j++)
                o.add(i == j);
            samples.add(o);
        }

    }

    public short getCntTreadles() {
        return cntTreadles;
    }

    public void setCntTreadles(short newCntTreadles) throws Exception {
        if (newCntTreadles <= 0)
            throw new IllegalArgumentException("newCntTreadles is negative");
        if (cntTreadles == newCntTreadles)
            return;
        for (short i = 0 ; i < cntHeddles ; i++ ) {
            ArrayList<Boolean> o = (ArrayList<Boolean>)samples.get(i);
            if (cntTreadles > newCntTreadles) {
                for (short j = (short)(cntTreadles - 1); j >= newCntTreadles; j-- ) {
                    o.remove(j);
                }
            } else {
                short k = (short)(((i % 2) == 0) ? i + 1 : i - 1);
                for (short j = cntTreadles; j < newCntTreadles; j++) {
                    if ((isRoll)&&(k < i)) {
                        o.add(!getIsUp(k, j));
                    } else {
                        o.add(i == j);
                    }
                }
            }
        }
        if (cntTreadles > newCntTreadles) {
            for (short i = 0; i < cntWefts; i++) {
                Weft w = Wefts.get(i);
                if (w.numberTreadle >= newCntTreadles) {
                    w.numberTreadle = 0;
                }
            }
        }
        this.cntTreadles = newCntTreadles;
    }

    public short getCntHeddles() {
        return cntHeddles;
    }

    public void setCntHeddles(short newCntHeddles) throws Exception {
        if (newCntHeddles <= 0)
            throw new IllegalArgumentException("newCntHeddles is negative");
        if ((isRoll)&&((newCntHeddles % 2) == 1))
            throw new IllegalArgumentException("newCntHeddles is not even");

        if (cntHeddles > newCntHeddles) {
            for (short i = (short)(cntHeddles - 1) ; i >= newCntHeddles ; i-- ) {
                samples.remove(i);
            }
            for (short i = 0; i < cntWarps; i++) {
                Warp w = Warps.get(i);
                if (w.numberHeddle >= newCntHeddles) {
                    w.numberHeddle = 0;
                }
            }
        } else {
            for (short i = cntHeddles ; i < newCntHeddles ; i++ ) {
                ArrayList<Boolean> o = (ArrayList<Boolean>)samples.get(i);
                short k = (short)(((i % 2) == 0) ? i + 1 : i - 1);
                for (short j = 0; j < cntTreadles; j++) {
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

    public short getCntWarps() {
        return cntWarps;
    }

    public void setCntWarps(short newCntWarps) throws Exception {
        if (newCntWarps <= 0)
            throw new IllegalArgumentException("newCntWarps is negative");
        if (cntWarps > newCntWarps) {
            for (short i = (short)(cntWarps - 1) ; i >= newCntWarps ; i-- ) {
                Warps.remove(i);
            }
        } else {
            short tIndex = (short)(Warps.get(cntWarps - 1).numberHeddle + 1);
            Color cl = Warps.get(cntWarps - 1).color;
            for (short i = cntWarps; i < newCntWarps; i++) {
                if (tIndex >= cntHeddles)
                    tIndex = 0;
                Warps.add(new Warp(tIndex, cl));
                tIndex++;
            }
        }
        this.cntWarps = newCntWarps;
    }

    public short getCntWefts() {
        return cntWefts;
    }

    public void setCntWefts(short newCntWefts) {
        if (newCntWefts <= 0)
            throw new IllegalArgumentException("newCntWarps is negative");
        if (cntWefts > newCntWefts) {
            for (short i = (short)(cntWefts - 1) ; i >= newCntWefts ; i-- ) {
                Wefts.remove(i);
            }
        } else {
            short tIndex = (short)(Wefts.get(cntWefts - 1).numberTreadle + 1);
            Color cl = Wefts.get(cntWefts - 1).color;
            for (short i = cntWefts; i < newCntWefts; i++) {
                if (tIndex >= cntTreadles)
                    tIndex = 0;
                Wefts.add(new Weft(tIndex, cl));
                tIndex++;
            }
        }
        this.cntWefts = newCntWefts;
    }

    public Color getColorWarp(short Index) {
        if ((Index < 0)||(Index >= cntWarps))
            throw new IndexOutOfBoundsException("Index is out Of bounds");
        return Warps.get(Index).color;
    }

    public void setColorWarp(short Index, Color cl) {
        if ((Index < 0)||(Index >= cntWarps))
            throw new IndexOutOfBoundsException("Index is out Of bounds");
        Warps.get(Index).setColor(cl);
    }

    public short getHeddleWarp(short Index) {
        if ((Index < 0)||(Index >= cntWarps))
            throw new IndexOutOfBoundsException("Index is out Of bounds");
        return Warps.get(Index).numberHeddle;
    }

    public void setHeddleWarp(short Index, short Heddle) {
        if ((Index < 0)||(Index >= cntWarps))
            throw new IndexOutOfBoundsException("Index is out Of bounds");
        if ((Heddle < 0)||(Heddle >= cntHeddles))
            throw new IndexOutOfBoundsException("Index is out Of bounds");
        Warps.get(Index).setNumberHeddle(Heddle);
    }

    public Color getColorWeft(short Index) {
        if ((Index < 0)||(Index >= cntWefts))
            throw new IndexOutOfBoundsException("Index is out Of bounds");
        return Wefts.get(Index).color;
    }

    public void setColorWeft(short Index, Color cl) {
        if ((Index < 0)||(Index >= cntWefts))
            throw new IndexOutOfBoundsException("Index is out Of bounds");
        Wefts.get(Index).setColor(cl);
    }

    public short getTreadleWeft(short Index) {
        if ((Index < 0)||(Index >= cntWefts))
            throw new IndexOutOfBoundsException("Index is out Of bounds");
        return Wefts.get(Index).numberTreadle;
    }

    public void setTreadleWeft(short Index, short Treadle) {
        if ((Index < 0)||(Index >= cntWefts))
            throw new IndexOutOfBoundsException("Index is out Of bounds");
        if ((Treadle < 0)||(Treadle >= cntTreadles))
            throw new IndexOutOfBoundsException("Treadle is out Of bounds");
        Wefts.get(Index).setNumberTreadle(Treadle);
    }

    public Boolean getIsSampleUp(short IndexHeddle, short IndexTreadle) throws Exception {
        return ((ArrayList<Boolean>) samples.get(IndexHeddle)).get(IndexTreadle);
    }

    public void setIsSampleUp(short IndexHeddle, short IndexTreadle, boolean isUp) throws Exception {
        ArrayList<Boolean> o = (ArrayList<Boolean>) samples.get(IndexHeddle);
        o.set(IndexTreadle, isUp);
    }

    public Boolean getIsUp(short IndexWarp, short IndexWeft) throws Exception {
        return getIsSampleUp(getHeddleWarp(IndexWarp), getTreadleWeft(IndexWeft));
    }

    public Color getColor(short IndexWarp, short IndexWeft) throws Exception {
        Boolean up = getIsUp(IndexWarp, IndexWeft);
        Color res = null;
        if (up) {
            res = getColorWarp(IndexWarp);
        } else {
            res = getColorWeft(IndexWeft);
        }
        return res;
    }

    protected void forSaveFile_getPattern(WeavePatternType weavePattern) {
        try {
            LoomPatternType loomPattern = new LoomPatternType();
            SampleType sampleType = new SampleType();
            sampleType.setTreadlesCount((short) cntTreadles);
            sampleType.setHeddlesCount((short) cntHeddles);
            for (short i = 0; i < cntHeddles; i++) {
                for (short j = 0; j < cntTreadles; i++) {
                    SampleElementType el = new SampleElementType();
                    el.setHeddle(i);
                    el.setTreadle(j);
                    el.setIsUp(getIsSampleUp(i, j));
                    sampleType.getSampleElement().add(el);
                }
            }
            loomPattern.setSample(sampleType);
            WarpsType warpsType = new WarpsType();
            warpsType.setCount((short) Warps.size());
            short idx = 0;
            for (Warp w : this.Warps) {
                WarpType warpType = new WarpType();
                warpType.setColor(w.color.toString());
                warpType.setHeddle(w.numberHeddle);
                warpType.setIndex(idx++);
                warpsType.getWarp().add(warpType);
            }
            loomPattern.setWarps(warpsType);
            weavePattern.setLoomPattern(loomPattern);
        } catch (Exception e) {
        }
    }


//    @Override
//    public void savetoFile(String path) {
//
//    }

}
