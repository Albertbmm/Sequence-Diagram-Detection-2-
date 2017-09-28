package javaparsermodel.process;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;
import java.util.*;
import javaparsermodel.InisiasiCall;
import javaparsermodel.InisiatorCall;
import javaparsermodel.Method;
import javaparsermodel.MethodPublic;
import javaparsermodel.NamaClass;
import javaparsermodel.PemanggilanMethod;
import data.*;

public class CodeParserStack extends DefaultHandler {
    
   private Stack tagStack = new Stack();
   // Local list of Method call...
   private ArrayList<Method> methodCode= new ArrayList<Method>();
   private ArrayList<InisiasiCall> initCode = new ArrayList<InisiasiCall>();
   private ArrayList<InisiatorCall>inisiasiCode = new ArrayList<InisiatorCall>();
   private ArrayList<NamaClass> namaClassCode = new ArrayList<NamaClass>();
   private ArrayList<MethodPublic> methodPublicCode = new ArrayList<MethodPublic>();
   private ArrayList<PemanggilanMethod> panggilMethod = new ArrayList<PemanggilanMethod>();
   
   //Declare Variable untuk NamaClass Data Model
   private String isiModifierClass;
   private String isiTipeClass;
   
   //Declare Variabel untuk Method Data Model
   //untuk func yang return
   String typePublicTemp;
   String methodPublicTemp;//modifier
   String namaMethodValue; //untuk yang sifat nya function return
    //untuk yang sifatnya function void
   
   //untuk void
   String namaMethodVoid;
   String tipeVoid;
   //String methodVoidTemp; //apabila modifier nya public void
   String modifierVoid="";//untuk modifier void
   
   //String untuk pemanggilan method dalam class lain 
   String namaMethodCall;
   
   //pemanggilan method nya
   String isiReturn;
   String inisiatorClass;
   private Method methodCodedata;
   private InisiasiCall initCodedata;
   private InisiatorCall inisiasiCodedata;
   private NamaClass namaClassData;
   private MethodPublic methodPublicCodeData;
   private PemanggilanMethod panggilMethodData;
   private CharArrayWriter contents = new CharArrayWriter(); //untuk mengambil char dari xml
   private Proyek proyek ;
   private Kelas kelasData;
   private Metode metodeData; //untuk menyimpan list of kode sumber
   private BarisKodeSumber barisKodeData; //untuk mendeklare struktur data baris kode sumber
   public void setProject(Proyek proyek) {
       this.proyek = proyek;
   }
   
   
   public void startElement( String namespaceURI,
               String localName,
              String qName,
              Attributes attr ) throws SAXException {
     
      namaClassData = new NamaClass();
      methodCodedata = new Method();
      methodPublicCodeData = new MethodPublic();
      initCodedata = new InisiasiCall();
      inisiasiCodedata = new InisiatorCall(); //untuk mengambil line yang pemanggilan method dalam class lain
      
      kelasData = new Kelas();
      proyek = new Proyek();
     
       contents.reset();
     
      tagStack.push( localName );
      
    
   }
   public void endElement( String namespaceURI,
               String localName,
              String qName ) throws SAXException {
       

       if ( getTagPath().equals( "/unit/CLASS/IDENT" ) ) {
         String isiNamaClass = contents.toString().trim();
         String tipeClass = "class";
         //harus declare sesuai,arraylist nya belum diinisasi
         Kelas kelas = new Kelas(isiNamaClass,isiModifierClass,tipeClass);
         proyek.addKelas(kelas);
         proyek.checkNamaClass();
         namaClassData.setNamaClass(isiNamaClass);
         namaClassData.setModifierClass(isiModifierClass);
         namaClassData.setTypeReturnClass(tipeClass);
         namaClassCode.add(namaClassData);
      }
      
       if(getTagPath().equals("/unit/CLASS/MODIFIER_LIST/PUBLIC")){
          //modifier untuk class
          isiModifierClass = contents.toString().trim();
          
      }
       
      //FUNCTION THAT HAS RETURN VALUE
      
      if(getTagPath().equals("/unit/CLASS/CLASS_TOP_LEVEL_SCOPE/FUNCTION_METHOD_DECL/MODIFIER_LIST/PUBLIC")){
          methodPublicTemp = contents.toString().trim();
      }
      if(getTagPath().equals("/unit/CLASS/CLASS_TOP_LEVEL_SCOPE/FUNCTION_METHOD_DECL/TYPE/QUALIFIED_TYPE_IDENT/IDENT")){
          //untuk tipe return value 
          typePublicTemp = contents.toString().trim();
      }
      if(getTagPath().equals("/unit/CLASS/CLASS_TOP_LEVEL_SCOPE/FUNCTION_METHOD_DECL/IDENT")){
          namaMethodValue = contents.toString().trim();
      } 
      if(getTagPath().equals("/unit/CLASS/CLASS_TOP_LEVEL_SCOPE/FUNCTION_METHOD_DECL/FORMAL_PARAM_LIST/IDENT")){
          //untuk argumen parameter nya 
         
      }
      if(getTagPath().equals("/unit/CLASS/CLASS_TOP_LEVEL_SCOPE/FUNCTION_METHOD_DECL/BLOCK_SCOPE/RETURN")){
          //untuk return value nya
          Metode methodData = new Metode(namaMethodValue,typePublicTemp,methodPublicTemp);
          kelasData.insertMetode(methodData);
          kelasData.checkIsiMetode();
          //Set value ke data model
          methodCodedata.setModifier(methodPublicTemp);
          methodCodedata.setTypeReturn(typePublicTemp);
          methodCodedata.setNamaMethod(namaMethodValue);
          //methodCodedata.setArgumen(qName);
//          methodPublicCodeData.setModifierReturn(methodPublicTemp);
//          methodPublicCodeData.setTypeReturn(typePublicTemp);
//          methodPublicCodeData.setNamaMethodReturn(namaMethodValue);
//          methodPublicCode.add(methodPublicCodeData);
          methodCode.add(methodCodedata);
      }
      
      //FUNGSI VOID
      
      if(getTagPath().equals("/unit/CLASS/CLASS_TOP_LEVEL_SCOPE/VOID_METHOD_DECL/MODIFIER_LIST/PUBLIC")){
          modifierVoid = contents.toString().trim();
      }
        
     if(getTagPath().equals("/unit/CLASS/CLASS_TOP_LEVEL_SCOPE/VOID_METHOD_DECL/IDENT")){
          tipeVoid ="void";
          namaMethodVoid = contents.toString().trim();
          //kalo misal void tidak ada modifier nya ?
          //di masukkan saja jadi 1 if semua (if untuk modifierVoid != null , dan else
          //Metode methodDataVoid = new Metode(namaMethodVoid,tipeVoid,modifierVoid);
          //kelasData.insertMetode(methodDataVoid);
//          methodCodedata.setNamaMethod(namaMethodVoid);
          if(!modifierVoid.isEmpty()){
              methodCodedata.setModifier(modifierVoid);
              methodCodedata.setNamaMethod(namaMethodVoid);
              methodCodedata.setTypeReturn(tipeVoid);
              methodCode.add(methodCodedata);
              Metode methodDataVoid = new Metode(namaMethodVoid,tipeVoid,modifierVoid);
              kelasData.insertMetode(methodDataVoid);
              kelasData.checkIsiMetode();
          }
          else{
              methodCodedata.setNamaMethod(namaMethodVoid);
              methodCodedata.setTypeReturn(tipeVoid);
              methodCode.add(methodCodedata);
              Metode methodDataVoid = new Metode(namaMethodVoid,tipeVoid,modifierVoid);
              kelasData.insertMetode(methodDataVoid);
              kelasData.checkIsiMetode(); 
              
          }
      }
     
     //VOID-PEMANGGILAN CLASS LAIN DALAM VOID
     //mengisi set ke data model . 
     //declare Variabel ke global
      if(getTagPath().equals("/unit/CLASS/CLASS_TOP_LEVEL_SCOPE/VOID_METHOD_DECL/BLOCK_SCOPE/VAR_DECLARATION/TYPE/QUALIFIED_TYPE_IDENT/IDENT")){
         //Deklarasi untuk mendapatkan inisator dari code caller , contoh : Lifeline2 dua = (lifeline2 itu ini)
         //initCodedata = new InisiasiCall(); //untuk yang inisiasi class lain
         inisiatorClass = contents.toString().trim();
         //initCodedata.setNamaClassInisiator(inisiatorClass);
         //initCode.add(initCodedata);
         
         
      }
      
      if(getTagPath().equals("/unit/CLASS/CLASS_TOP_LEVEL_SCOPE/VOID_METHOD_DECL/BLOCK_SCOPE/VAR_DECLARATION/VAR_DECLARATOR_LIST/VAR_DECLARATOR/IDENT")){
        //ini isiya adalah deklarasi untuk memanggil method di dalam class lain (didalam contoh : 'dua')
        
        namaMethodCall = contents.toString().trim();
        initCodedata.setNamaMethodCaller(namaMethodCall);
        initCodedata.setNamaClassInisiator(inisiatorClass);
        initCode.add(initCodedata);
       
      }
         
      if ( getTagPath().equals( "/unit/CLASS/CLASS_TOP_LEVEL_SCOPE/VOID_METHOD_DECL/BLOCK_SCOPE/EXPR/METHOD_CALL/DOT/IDENT" ) ) {
         //memanggil call method lain
         
          isiReturn =contents.toString().trim();
         inisiasiCodedata.setNamaInisiasi(isiReturn);
         inisiasiCode.add(inisiasiCodedata);
      }
     
      // clean up the stack...
      tagStack.pop();
   }
   public void characters( char[] ch, int start, int length )
                  throws SAXException {
      // accumulate the contents into a buffer.
      contents.write( ch, start, length );
   }

   
   private String getTagPath( ){
      //  build the path string...
      String buffer = "";
      Enumeration e = tagStack.elements();
      while( e.hasMoreElements()){
               buffer  = buffer + "/" + (String) e.nextElement();        
      }
      return buffer;
   }

   public void getClassMethodCaller(){
       panggilMethodData = new PemanggilanMethod();
       metodeData = new Metode("","","");
       String temp1 ="";
       String temp2 ="";        
       int sizeInitCode = initCode.size();
       int sizeInisiasiCode = inisiasiCode.size();
       for(int i=0;i<sizeInitCode;i++){
           for(int y=0;y<sizeInisiasiCode;y++)
           {
               //disini untuk memasukkan ke data struktur yang baru
               
               String namaMethodCallerTemp;
               String datanya = inisiasiCode.get(y).getNamaInisiasi().toString();
               String dataTemp = "";
               String dataMethod;
               namaMethodCallerTemp = initCode.get(i).getNamaMethodCaller().toString();
               if(datanya.equals(namaMethodCallerTemp)){
                   dataTemp = initCode.get(i).getNamaClassInisiator();
                   //System.out.println(dataTemp);
                   panggilMethodData.setVariablePemanggil(dataTemp);
                   temp1 = dataTemp;
                   //masukkan ke dalam arraylist yang baru untuk setting nya
                   
               }
               if(!datanya.equals(namaMethodCallerTemp)){
                   //System.out.println(datanya); //masukkan ke dalam arraylist
                   String datanyaSimpan = datanya;
                   panggilMethodData.setMethodDipanggil(datanya);
                   panggilMethod.add(panggilMethodData);
                   temp2 = datanya;
               }
           }
               barisKodeData = new BarisKodeSumber(temp1,temp2);
               metodeData.insertBarisKode(barisKodeData);
               metodeData.checkBarisKode();
       }
   }
   
   public void checkIsiArrayList(){
      //kelasData = new Kelas();
     
      //kelasData.checkIsiMetode();
       
   }
   public void readList(){
       //proses pembacaan disini
       for(int i=0;i<namaClassCode.size();i++){
           System.out.println(namaClassCode.get(i).toString());
       }
       for(int i=0;i<methodCode.size();i++){
           System.out.println(methodCode.get(i).toString());
       }
       
       
       System.out.println();
       for(int i=0;i<panggilMethod.size();i++){
           System.out.println(panggilMethod.get(i).toString());
       }
       
   }
   
   public static void main( String[] argv ){
      try {
          
         // Create SAX 2 parser...
         XMLReader xr = XMLReaderFactory.createXMLReader();
         // Set the ContentHandler...
         CodeParserStack ex1 = new CodeParserStack();
         xr.setContentHandler( ex1 );
         // Parse the file...
         xr.parse( new InputSource(
               new FileReader( "src/xml/MenuUI.xml" )) );
        ex1.getClassMethodCaller();
        System.out.println();
        ex1.readList();
      }catch ( Exception e )  {
         e.printStackTrace();
      }
   }
}