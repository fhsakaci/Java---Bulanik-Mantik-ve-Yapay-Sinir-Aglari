/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g141210402;
import java.io.File;
import java.net.URISyntaxException;
import net.sourceforge.jFuzzyLogic.FIS;

/**
 *
 * @author SAKACI
 */
public class Helikopter {
    private FIS fis;
    private double gkuvvet;
    private double kutle;
    private double acisal;
    
    public Helikopter(double v1,double v2,double v3)throws URISyntaxException
    {
        gkuvvet=v1;kutle=v2;acisal=v3;
        File dosya=new File(getClass().getResource("myfcl.fcl").toURI());
        fis=FIS.load(dosya.getPath(),true);
        fis.setVariable("gKuvvet",gkuvvet );
        fis.setVariable("kutle",kutle );
        fis.setVariable("acisal",acisal );
        fis.evaluate();
    }

    public Helikopter(double k, double t) throws URISyntaxException{
        throw new UnsupportedOperationException("Yetersiz değişken"); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Helikopter(double k) throws URISyntaxException{
        throw new UnsupportedOperationException("Yetersiz değişken"); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Helikopter() throws URISyntaxException{
        File dosya=new File(getClass().getResource("myfcl.fcl").toURI());
        fis=FIS.load(dosya.getPath(),true);
    }
    public FIS getModel()
    {
        return fis;
    }
    @Override
    public String toString() {
        String cikti=""+fis.getVariable("durum").getValue()/100;
        return cikti;
    }
}
