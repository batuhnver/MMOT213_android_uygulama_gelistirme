package com.example.turkey

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_city_details.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.city_main.view.*




class MainActivity : AppCompatActivity() {
    var adapter: CityAdapter? = null
    var cityList = ArrayList<City>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Şehirleri yükle.
        cityList.add(City("İstanbul", "Nüfus: 15,52 milyon(31 Ara 2019)\n\nİklim Bilgileri\nİklim ve Bitki Örtüsü İklimi: Akdeniz, Karadeniz, Balkan ve Anadolu kara ikliminin tesiri altında bulunur. Kışın Akdeniz'den gelen ılık lodosları, Balkanlar üzerinden gelen soğuk veya Karadeniz'den gelen yağışlı havalar tâkip eder.\n\nCoğrafi Bilgiler\nİstanbul, Avrupa ile Asya kıtaları arasında köprü görevi gören, bunların birbirine en çok yaklaştığı iki uç üzerinde kurulmuş bir şehirdir. Şehrin adını aldığı ve Haliç ile Marmara arasında kalan yarımada üzerinde bulunan asıl İstanbul 253 km², bütünü ise 5712 km² 'dir. ..",R.drawable.istanbul))
        cityList.add(City("Adana", "Nüfus: 2,22 milyon(2019)\n\nİklim Bilgileri\nAdana, Akdeniz iklim özelliklerini taşır. Yazları sıcak ve kurak, kışları ılık ve yağışlıdır. Bölgede meydana gelen yağışlar, genellikle yamaç yağışları ve gezici hava kütlelerinin karşılaşması ile oluşur. ... Yağışlar %51 kışın, %26 ilkbaharda, %18 sonbaharda, %5 yazın düşer.\n\nCoğrafi Bilgiler\nAdana İli 35-38 enlemleri ile 34- 46 doğu boylamları arasında ve Akdeniz Bölgesi'nde yer almaktadır. Kuzeyinde Kayseri, doğusunda Kahramanmaraş ve Gaziantep, batısında Niğde ve İçel güneydoğusunda Hatay illeri bulunur.",R.drawable.adana))
        cityList.add(City("Ankara", "Nüfus: 5,504 milyon(2019) \n\nİklim Bilgileri\nGenellikle kara ikliminin hüküm sürdüğü Ankara'da farklı iklimler vardır. Güneyde İç Anadolu'nun hususiyeti olan step-bozkır iklimi, kuzeyde ise Karadeniz bölgesinin yumuşak ve yağışlı özelliği görülür. Ankara ilinin kışları çok soğuk ve yazları da çok sıcaktır.\n\nCoğrafi Bilgiler\nAnkara ili, doğuda Kırıkkale, kuzeydoğuda Çankırı, kuzeybatıda Bolu, batıda Eskişehir, güneyde Konya, güneydoğuda Kırşehir ve Aksaray ile komşudur. ... İlin güneyinde ise 1300 km² ile ülkenin en büyük ikinci gölü, %32,4 tuz oranıyla da dünyanın en tuzlu ikinci gölü olan Tuz Gölü vardır.",R.drawable.ankara))
        cityList.add(City("Bursa", "Nüfus: 3,56 milyon(31 Ara 2019)\n\nİklim Bilgileri\nDenizden yüksekliği 155 metre olan Bursa, genelde ılıman bir iklime sahiptir. ... Kuzeyde Marmara Denizi'nin yumuşak ve ılık iklimine karşılık güneyde Uludağ'ın sert iklimi ile karşılaşılmaktadır.\n\nCoğrafi Bilgiler\nBursa ili doğuda Bilecik, Adapazarı, kuzeyde Kocaeli, Yalova, İstanbul ve Marmara Denizi, güneyde Kütahya, batıda Balıkesir illeriyle çevrilidir. ",R.drawable.bursa))
        cityList.add(City("Eskişehir", "Nüfus: 871.187(31 Ara 2019)\n\nİklim Bilgileri\nEskişehir, coğrafi şartları, yükseltileri, yeryüzü şekilleri, denize olan uzaklığı gibi nedenlerden dolayı kara iklimi özelliğine sahiptir. ... Genellikle Eskişehir'de kışlar parçalı bulutlu, kar yağışlı, baharlar orta derecede yağışlı ve yazlar ise az bulutlu ve açık geçer. \n\nCoğrafi Bilgiler\nEskişehir, İç Anadolu Bölgesi'nin kuzeybatısında 29-32 derece doğu boylamları ile 39-40 derece kuzey enlemleri arasında yer almaktadır. Eskişehir'in yüzölçümü 13.652 km²'dir. En Yüksek dağı 1825 metre ile Türkmendağı Tepesidir.",R.drawable.eskisehir))
        cityList.add(City("İzmir", "Nüfus: 4,321 milyon(2019)\n\nİklim Bilgileri\nİzmir ilinde Akdeniz iklimi hüküm sürer. Yazları kurak ve sıcak kışları ılık ve yağışlı geçer. Temmuz-ağustos ayları en sıcak ve ocak-şubat en soğuk aylardır. Sıfırın altında gün sayısı 10 günü geçmez.\n\nCoğrafi Bilgiler\nYüzölçümü: 12.012 km² Jandarma Sorumululuk Sahası: 10.807 km²'dir. Nüfus: 4.113.072 (2015) Nüfüsun %13'ü (536.735) Jandarma sorumluluk sahasındadır. İzmir, Türkiye'nin üçüncü büyük kentidir. İzmir, Ege kıyı bölgesinin tipik bir örneği gibidir.",R.drawable.izmir))
        cityList.add(City("Konya", "Nüfus: 2,206 milyon(2019)\n\nİklim Bilgileri\nKONYA - İklim ve Bitki Örtüsü İklimi: İç Anadolu bölgesinin güney kısmında yer alan Konya'da kışlar sert, soğuk ve kar yağışlı, yazlar sıcak ve kurak geçer. Yıllık ortalama sıcaklık 11,5°C'dir. Rastlanan en yüksek sıcaklık 40°C, en düşük ise -28,2°C'dir.\n\nCoğrafi Bilgiler\nlimiz topraklarının büyük bir bölümü, İç Anadolu'nun yüksek düzlükleri üzerine rastlar.Güney ve güneybatı kesimleri Akdeniz bölgesine dahildir.Konya,coğrafi olarak 36° 41' ve 39 16' kuzey enlemleri ile 31° 14' ve 34° 26' doğu boylamları arasında yer alır.Yüzölçümü 38257 km (göller hariç)'dir.",R.drawable.konya))
        cityList.add(City("Ordu", "Nüfus: 771.932(31 Ara 2019)\n\nİklim Bilgileri\nOrdu ilinde tipik Karadeniz iklimi hüküm sürer. Kışlar ılık yazlar ise serin geçer. Sâhilden içeriye indikçe kara iklimi görülür. Canik Dağları kuzeyden gelen soğuk kuzey rüzgârlarına set olur.\n\nCoğrafi Bilgiler\nOrdu il merkezi 41° kuzey paraleli ve 37° ve 38° doğu meridyenleri arasında, Karadeniz Bölgesi'nin, Doğu Karadeniz bölümünde(Bölgesinde) yer almaktadır. İlin kuzeyini Kuzey Anadolu dağlarının kıyı sıraları kaplamaktadır. Kıyılara yakın tepelerle başlayan bu dağlar içeri doğru gittikce yükselir.",R.drawable.ordu))
        cityList.add(City("Samsun", "Nüfus: 1,336 milyon (2019)\n\nİklim Bilgileri\nSamsun genellikle ılıman bir iklime sahiptir. Ancak sahil şeridi ve iç kesimlerinde iklim iki ayrı özellik gösterir. Sahil şeridinde (Merkez ilçe, Terme, Çarşamba, Bafra, Alaçam, 19 Mayıs, Tekkeköy ve Yakakent) Karadeniz ikliminin etkileri görülür. Bunun için sahil şeridinde yazlar sıcak, kışlar ılık ve yağışlı geçer.\n\nCoğrafi Bilgiler\nSamsun, Karadeniz Bölgesi'nin Orta Karadeniz Bölümü içerisindeki Canik Dağları Yöresi'nde yer almaktadır. Karadeniz'e doğru akarak iki büyük delta oluşturan Kızılırmak ve Yeşilırmak nedeniyle Samsun kıyıları geniş bir koy niteliğini almıştır.",R.drawable.samsun))
        cityList.add(City("Antalya", "Nüfus: 2,426 milyon (2019)\n\nİklim Bilgileri\nAntalya ili iklimi genel olarak Akdeniz iklimine girmektedir. Yazları sıcak ve kurak, kışları ılık ve yağışlı olarak ifade edilen iklim tipi diğer bir değişle mutedil deniz ve sıcak deniz iklim sınıfına girer, daha iç kesimlerde ise soğuk ve yarı-kara iklim tipi görülmektedir.\n\nCoğrafi Bilgiler\nGüneyinde Akdeniz ve kuzeyinde denize paralel uzanan Toroslar ile çevrili olup, doğusunda Mersin, Konya ve Karaman, kuzeyinde Isparta ve Burdur, batısında Muğla illeri ile komşudur. İlin yüzölçümü 20.815 km² kadardır. Bu Türkiye yüzölçümünün % 2,6' sı kadarına karşılık gelir.",R.drawable.antalya))



        adapter = CityAdapter(this, cityList)
        gvCitys.adapter = adapter
    }

    class CityAdapter : BaseAdapter {
        var cityList = ArrayList<City>()
        var context: Context? = null

        constructor(context: Context, cityList: ArrayList<City>) : super() {
            this.context = context
            this.cityList = cityList
        }

        override fun getCount(): Int {
            return cityList.size
        }

        override fun getItem(position: Int): Any {
            return cityList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val city = this.cityList[position]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var cityView = inflator.inflate(R.layout.city_main, null)
            cityView.imgCity.setImageResource(city.image!!)
            cityView.txtName.text = city.name!!

            cityView.setOnClickListener{
                var intent = Intent(context, CityDetailsActivity::class.java)
                intent.putExtra("name",city.name!!)
                intent.putExtra("des",city.des!!)
                intent.putExtra("image",city.image!!)
                context!!.startActivity(intent)

            }

            return cityView
        }
    }
}

