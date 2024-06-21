package Activities

import Fragments.Accepted_Requests_Fragment
import Fragments.Create_Request_Fragment
import Fragments.Home_fragment
import Fragments.Profile_Fragment
import Fragments.Rewards_Fragment
import SharedPreferences.SharedPreferencesManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.gharaana.nitabuddy.R
import com.gharaana.nitabuddy.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var SharedPreferencesManager: SharedPreferencesManager
    private lateinit var vibrator: Vibrator
    private lateinit var jsonObject: JSONObject

    fun <T> addtoRequestQueue(request: Request<T>){
        requestQueue.add(request)
    }

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SharedPreferencesManager= SharedPreferencesManager(this)
        vibrator=getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        //If User Not Logged In, Send it to Login Activity
        if(SharedPreferencesManager.getLoginState()==false){
            startActivity(Intent(this, User_Login_Activity::class.java))
            finish()
        }

        checkTokenStatus()


        //Default Fragment on StartUp
        replaceFragment(Home_fragment())
        binding.bottomNavigation.setOnItemSelectedListener {

            when(it.itemId){
                R.id.home -> replaceFragment(Home_fragment())
                R.id.accepted-> replaceFragment(Accepted_Requests_Fragment())
                R.id.createRequest -> replaceFragment(Create_Request_Fragment())
                R.id.rewards -> replaceFragment(Rewards_Fragment())
                R.id.profile -> replaceFragment(Profile_Fragment())

            }

            return@setOnItemSelectedListener true
        }
    }

    public fun replaceFragment(fragment: Fragment){
       val fragmentManager=supportFragmentManager
       val fragmentTransaction=fragmentManager.beginTransaction()
       fragmentTransaction.replace(R.id.frameLayout, fragment)
       fragmentTransaction.commit()
       vibrator.vibrate(50)
   }


    // TOken Expire check API
    private fun checkTokenStatus() {
        jsonObject= JSONObject()
        val url = "https://gharaanah.onrender.com/engineering/tokenauth"
        val request = object : StringRequest(
            Method.POST, url,
            { response ->
                val isTokenValid = response.toBoolean()

                if (isTokenValid){
                    //Nothing to do
                    //Toast.makeText(this, "Login Valid", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "Login Expired", Toast.LENGTH_SHORT).show()
                    logout()
                }
            },
            {
                Toast.makeText(this, "Some Error Occurred", Toast.LENGTH_SHORT).show()
            }
        ) {
            // Override getHeaders to add the Authorization header
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                val token = SharedPreferencesManager.getUserToken()
                headers["Authorization"] = "Bearer $token"
                return headers
            }
        }

        addtoRequestQueue(request)

    }

    private fun logout() {
        SharedPreferencesManager.updateLoginState(false)
        SharedPreferencesManager.updateUserToken("")
        startActivity(Intent(this, User_Login_Activity::class.java))
        finish()
    }
}
