/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g141210402;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.Scanner;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.Perceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;

/**
 *
 * @author SAKACI
 */
public class YSA {
   private static final File dosya = new File(YSA.class.getResource("dosya.txt").getPath());
    BackPropagation bp;
    int veriAdet;
    double maksIterasyon;
    double[] eldeEdilenHatalar;
    
    public YSA(double maksIterasyon){
        bp = new BackPropagation();
        this.maksIterasyon = maksIterasyon;
        eldeEdilenHatalar = new double[(int)maksIterasyon];
    }
    public void egit() throws FileNotFoundException{
        NeuralNetwork sinirselAg = new MultiLayerPerceptron(TransferFunctionType.TANH, 3, 10, 1);
        bp.setLearningRate(0.2);
        bp.setMaxError(0.01);
        sinirselAg.setLearningRule(bp);
        for(int i = 0; i < maksIterasyon; i++){
            ((BackPropagation)sinirselAg.getLearningRule()).doOneLearningIteration(EgitimVeriSeti());
            if(i == 0)  eldeEdilenHatalar[i] = 1;
            else    eldeEdilenHatalar[i] = ((BackPropagation)sinirselAg.getLearningRule()).getPreviousEpochError();
        }
        sinirselAg.save("ogrenenAg.nnet");
        System.out.println("Egitim Tamamlandi");
    }
    public DataSet EgitimVeriSeti() throws FileNotFoundException{
        Scanner oku = new Scanner(dosya);
        DataSet egitim = new DataSet(3,1);
        int sayac=0;
        while(oku.hasNextDouble()){
            sayac++;
            oku.nextDouble();oku.nextDouble();
            oku.nextDouble();oku.nextDouble();
        }
        oku.reset();
        oku.close();
        oku = new Scanner(dosya);
        this.veriAdet=sayac;
        for(int i=0;i<sayac*0.7;i++)
        {
            egitim.addRow(new DataSetRow(new double[]{oku.nextDouble(), oku.nextDouble(), oku.nextDouble()},new double[]{oku.nextDouble()}));
        }
        oku.close();
        return egitim;
    }
    
    public void test() throws FileNotFoundException{
        NeuralNetwork ogrenmisAg = NeuralNetwork.createFromFile("ogrenenAg.nnet");
        Scanner oku = new Scanner(dosya);
        for(int i=0;i<this.veriAdet*0.7;i++)
        {
            oku.nextDouble();oku.nextDouble();
            oku.nextDouble();oku.nextDouble();
        }
        double toplam=0;
        int sayac=0;
        while(oku.hasNextDouble())
        {
            ogrenmisAg.setInput(oku.nextDouble(),oku.nextDouble(),oku.nextDouble());
            ogrenmisAg.calculate();
            double[] out=ogrenmisAg.getOutput();
            double err=oku.nextDouble()-out[0];
            toplam=toplam+Math.abs(err);
            sayac++;
        }
        System.out.println((toplam/sayac));
    }
    
    
    public double[] ManualTest(double x, double y,double z){
        NeuralNetwork ogrenmisAg = NeuralNetwork.createFromFile("ogrenenAg.nnet");
        ogrenmisAg.setInput(x,y,z);
        ogrenmisAg.calculate();
        return ogrenmisAg.getOutput();
    }
    
    public double[] hatalar(){
        return eldeEdilenHatalar;
    }
    
    public double hata(){
        return bp.getTotalNetworkError();
    }
}
