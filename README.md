
# Toplu Taşıma Hattı Planlaması Projesi

Bu proje, Kırklareli'nin üç farklı mahallesi için en uygun toplu taşıma hattını belirlemek amacıyla geliştirilmiştir. Projede; nüfus yoğunluğu, mevcut ulaşım altyapısı, maliyet analizi, çevresel etki ve sosyal fayda gibi kriterlere dayalı olarak mahallelerin ağırlıklı skorları hesaplanır. Softmax algoritması ile normalize edilen sonuçlar sayesinde, en uygun güzergah belirlenir ve sonuçlar OpenStreetMap üzerinde Java Swing aracılığıyla görselleştirilir.



## Ekran Görüntüleri


![Uygulama Ekran Görüntüsü](https://github.com/edizklu/toplu-tasima-hatti-plani/blob/main/images/ss.png?raw=true)


  
## Bilgisayarınızda Çalıştırın

Projeyi klonlayın:

```bash
  git clone https://github.com/edizklu/toplu-tasima-hatti-plani.git 
```

Proje dizinine gidin:

```bash
  cd toplu-tasima-hatti-plani
```

Gerekli paketleri yükleyin ve projeyi derleyin (Maven kullanılarak):

```bash
  mvn clean install
```

## Kullanım/Örnekler
Bu proje masaüstü (desktop) uygulaması olarak çalışmaktadır. Uygulama başlatıldığında, OpenStreetMap üzerinde mahallelerin işaretlendiğini ve softmax algoritması sonucunda en uygun güzergahın belirlenmiş olduğunu görürsünüz.
  
## Yol haritası

- Gerçek zamanlı veri entegrasyonu

- Ek mahalle verilerinin eklenmesi

- Kullanıcı arayüzü iyileştirmeleri (ör. detaylı analiz ekranları)

- Yeni coğrafi veri kaynaklarının entegrasyonu


  
## Yükleme 

Projeyi yerel makinenize klonlayıp Maven ile derlemek için:

```bash 
git clone https://github.com/edizklu/toplu-tasima-hatti-plani.git
cd toplu-tasima-hatti-plani
mvn clean install
```
    
## Geri Bildirim

Herhangi bir geri bildiriminiz veya öneriniz varsa, lütfen edizssenturk@gmail.com adresinden bize ulaşın.


  
## Sık Sorulan Sorular

#### Soru 1: Projede hangi Java sürümü kullanılmıştır?

Cevap: Proje, Java 8 veya üstü ile uyumlu olacak şekilde geliştirilmiştir.

#### Soru 2: OpenStreetMap verileri nasıl entegre edilmiştir?

Cevap: OpenStreetMap verileri, Java Swing ile entegre edilen JMapViewer kütüphanesi kullanılarak görselleştirilmiştir.

  
## Özellikler

- Mahallelerin kriterlerine göre ağırlıklı skor hesaplaması
- Softmax algoritması ile normalize edilmiş sonuçlar
- OpenStreetMap üzerinde görselleştirme (JMapViewer kullanılarak)
- Java Swing tabanlı kullanıcı arayüzü
- Maven ile kolay derleme ve paketleme

  
## Ortam Değişkenleri

Projeyi derlemek ve çalıştırmak için aşağıdaki bağımlılıkların sisteminizde yüklü olması gerekmektedir:

- #### Java:
  Proje, Java 8 veya üstü sürüm ile uyumludur.

- #### Maven:
  Projenin derlenmesi, paketlenmesi ve bağımlılıkların yönetilmesi için Maven kullanılmaktadır.

- #### JMapViewer:
  OpenStreetMap verilerini görselleştirmek için kullanılan Java kütüphanesidir. Bu bağımlılık, JOSM repository'sinden çekilmektedir. Maven projenize eklemek için pom.xml dosyanıza aşağıdaki dependency ve repository tanımını ekleyin:

```bash
<dependency>
    <groupId>org.openstreetmap.jmapviewer</groupId>
    <artifactId>JMapViewer</artifactId>
    <version>2.14</version>
</dependency>
```
```bash
<repository>
     <id>JOSM</id>
    <url>https://josm.openstreetmap.de/nexus/content/repositories/releases/</url>
 </repository>
```



  
## Demo

Projeyi çalıştırdığınızda, OpenStreetMap üzerinde hesaplanan mahalle verilerinin işaretlendiğini ve en uygun güzergahın vurgulandığını göreceksiniz.

  
## Çıkarılan Dersler

Bu projeyi geliştirirken algoritma analizi, yazılım mimarisi, GUI geliştirme ve OpenStreetMap entegrasyonu konularında önemli deneyimler edindim. Karşılaşılan zorluklar arasında mahalle verilerinin normalize edilmesi ve görsel entegrasyonun sağlanması yer almaktadır. Bu süreç, verilerin doğru şekilde işlenmesi ve kullanıcı dostu bir arayüz oluşturmanın önemini kavramamı sağladı.

  
## Lisans

Bu proje [GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0/) altında lisanslanmıştır.

<<<<<<< HEAD
  
=======
  
>>>>>>> 3b8f074 (İlk commit)
