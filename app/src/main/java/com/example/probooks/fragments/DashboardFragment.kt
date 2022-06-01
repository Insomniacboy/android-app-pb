package com.example.probooks.fragments

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.probooks.R
import com.example.probooks.databinding.FragmentDashboardBinding
import com.example.probooks.repository.Response
import com.example.probooks.viewmodels.DashboardViewModel
import com.example.probooks.viewmodels.HomeViewModel
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.net.URL
import java.util.concurrent.Executors

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var dashboardViewModel: DashboardViewModel
    var response = ""
    val post = ""
    val mURL = "https://probooks.space/YxAjdSjWv8aOpJX2LlfqF3VE3x4="
    // This property is only valid between onCreateView and
    // onDestroyView.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val root: View = inflater.inflate(R.layout.fragment_dashboard, container, false)

        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        val imageUrl1="https://probooks.space/custom-img/bank-asia.png"
        val imageUrl2="https://probooks.space/custom-img/baker-tilly.png"

        Picasso.get().load(imageUrl1).into(binding.partner1)
        Picasso.get().load(imageUrl2).into(binding.partner2)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Executors.newSingleThreadExecutor().execute {
            try {
                var response = URL("https://probooks.space/YxAjdSjWv8aOpJX2LlfqF3VE3x4=").readText()
                var gson = Gson()
                val post: Response = gson.fromJson(response, Response::class.java)
                val welcome: TextView = view.findViewById<TextView>(R.id.welcome)
                welcome.post { welcome.text = post.welcome }
                val desc_title: TextView = view.findViewById<TextView>(R.id.desc_title)
                desc_title.post { desc_title.text = post.descTitle }
                val desc: TextView = view.findViewById<TextView>(R.id.desc)
                desc.post { desc.text = post.desc }
                val price_title: TextView = view.findViewById<TextView>(R.id.price_title)
                price_title.post { price_title.text = post.priceTitle }
                val price_choose: TextView = view.findViewById<TextView>(R.id.price_choose)
                price_choose.post { price_choose.text = post.priceChoose }
                val price_opt1_title: TextView = view.findViewById<TextView>(R.id.price_opt1_title)
                price_opt1_title.post { price_opt1_title.text = post.priceOpt1Title }
                val price_opt1: TextView = view.findViewById<TextView>(R.id.price_opt1)
                price_opt1.post { price_opt1.text = post.priceOpt1 }
                val price_opt2_title: TextView = view.findViewById<TextView>(R.id.price_opt2_title)
                price_opt2_title.post { price_opt2_title.text = post.priceOpt2Title }
                val price_opt2: TextView = view.findViewById<TextView>(R.id.price_opt2)
                price_opt2.post { price_opt2.text = post.priceOpt2 }
                val price_opt2_adv: TextView = view.findViewById<TextView>(R.id.price_opt2_adv)
                price_opt2_adv.post { price_opt2_adv.text = post.priceOpt2Adv }
                val price_opt3_title: TextView = view.findViewById<TextView>(R.id.price_opt3_title)
                price_opt3_title.post { price_opt3_title.text = post.priceOpt3Title }
                val price_opt3: TextView = view.findViewById<TextView>(R.id.price_opt3)
                price_opt3.post { price_opt3.text = post.priceOpt3 }
                val price_opt3_adv: TextView = view.findViewById<TextView>(R.id.price_opt3_adv)
                price_opt3_adv.post { price_opt3_adv.text = post.priceOpt3Adv }
                val price_req: TextView = view.findViewById<TextView>(R.id.price_req)
                price_req.post { price_req.text = post.priceReq }
                val faq_title: TextView = view.findViewById<TextView>(R.id.faq_title)
                faq_title.post { faq_title.text = post.faqTitle }
                val faq_num1: TextView = view.findViewById<TextView>(R.id.faq_num1)
                faq_num1.post { faq_num1.text = post.faqNum1 }
                val faq_num2: TextView = view.findViewById<TextView>(R.id.faq_num2)
                faq_num2.post { faq_num2.text = Html.fromHtml(post.faqNum2?.replace("\\","")) }
                val partners_title: TextView = view.findViewById<TextView>(R.id.partners_title)
                partners_title.post { partners_title.text = post.partnersTitle }
                val partner1: TextView = view.findViewById<TextView>(R.id.partner1_text)
                partner1.post { partner1.text = post.partner1 }
                val partner2: TextView = view.findViewById<TextView>(R.id.partner2_text)
                partner2.post { partner2.text = post.partner2 }
                val contacts_title: TextView = view.findViewById<TextView>(R.id.contacts_title)
                contacts_title.post { contacts_title.text = post.contactsTitle }
                val mail_hyperlink: TextView = view.findViewById<TextView>(R.id.mail_hyperlink)
                mail_hyperlink.post { mail_hyperlink.text = Html.fromHtml(post.mailHyperlink?.replace("\\", "")) }
                val contacts: TextView = view.findViewById<TextView>(R.id.contacts)
                contacts.post { contacts.text = post.contacts }
                //Log.d("JSON", "$post.welcome")}
                }
                catch (ex : Exception) {
                Log.d("Error", "$ex")
            }

        }

    }
}