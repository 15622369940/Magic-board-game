package ch7.data;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
public class HandleImage extends JComponent{
    int imageWidth,imageHeight;
    Toolkit tool; 
    public HandleImage(){
       tool=getToolkit(); 
    }
    public Image [] getImages(Image image,int rows,int colums){
       Image [] blockImage=new Image[rows*colums];
       try{
            imageWidth=image.getWidth(this);
            imageHeight=image.getHeight(this);
            int w=imageWidth/colums;                                    
            int h=imageHeight/rows;
            int k=0; //把图像分成k份，即rows*colums份
            PixelGrabber pg=null;  
            ImageProducer ip=null;                 
            for(int i=0;i<rows;i++){
                for(int j=0;j<colums;j++){
                   int pixels[]= new int[w*h];//存放第k份图像的像素的数组 
                   //将图像image中（j*w,i*h,w,h）矩形区域的像素放到数组pixels的第0行至第w行中：
                   pg=new PixelGrabber(image,j*w,i*h,w,h,pixels,0,w);            
                   pg.grabPixels();                                              
                   ip=new  MemoryImageSource(w,h,pixels,0,w);//用数组pixels第0行至w行像素做图像源
                   blockImage[k]=tool.createImage(ip); //得到宽是w高是h的矩形Image对象
                   k++;            
                }
            }
       }
       catch(Exception ee){} 
       return blockImage;
    }
}