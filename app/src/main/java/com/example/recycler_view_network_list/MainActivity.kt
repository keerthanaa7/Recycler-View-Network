package com.example.recycler_view_network_list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.android.volley.Header
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.recycler_view_network_list.ui.theme.RecyclerviewnetworklistTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val url = "https://api.github.com/search/users?q=language:java+location:lagos"

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = VERTICAL
        recyclerView.layoutManager = layoutManager

        //createFruitList(this, recyclerView)

       // volleyNetworkList(this, recyclerView, url)

        val list = retrofitNetworkList(this, recyclerView)


    }
}

fun createFruitList(context: Context, recylerview: RecyclerView) {
    val fruitlist:List<String> = listOf("apple", "banana", "guava", "guava1", "banana1", "banana2", "banana3",
        "banana4", "banana5", "banana6", "apple5", "apple6")
    val fruitRecyclerViewAdapter = FruitRecyclerViewAdapter(context, fruitlist)
    recylerview.adapter = fruitRecyclerViewAdapter
}

fun volleyNetworkList(context: Context, recylerview: RecyclerView, url:String){
    // using VOLLEY HTTP
    val developerList = mutableListOf<Developer>()
    val queue = Volley.newRequestQueue(context)
    val request = JsonObjectRequest(Request.Method.GET, url,null,
        Response.Listener { response ->
            try {
                val jsonArray:JSONArray = response.getJSONArray("items")
                for(i in 0 until jsonArray.length()){
                    val subJsonObject = jsonArray.getJSONObject(i)
                    val node_id = subJsonObject.get("node_id")
                    val id = subJsonObject.get("id")
                    val login = subJsonObject.get("login")
                    val developer = Developer(node_id.toString(), login.toString(), id.toString())
                    System.out.println("node id " + developer.node_id)
                    developerList.add(developer)
                    val recyclerViewAdapter = RecyclerViewAdapter(context, developerList)
                    recylerview.adapter = recyclerViewAdapter
                }
            } catch (execption:JSONException){
                Log.d("Main activity " , "JSON exception")
            }
        }, Response.ErrorListener { error ->
            Log.d("Main activity " , " response error")
        })

    queue.add(request)
}

fun retrofitNetworkList(context: Context,  recylerview: RecyclerView):List<Picture> {
    val retrofitInstance = Retrofit.Builder().
    baseUrl(PictureService.ROOT_URL).
    addConverterFactory(GsonConverterFactory.create())
        .build()
    val pictureserviceInterface = retrofitInstance.create(PictureService::class.java)
    var catList:List<Picture> = mutableListOf<Picture>()
    GlobalScope.launch {
        try {
            catList = pictureserviceInterface.pictures()
            System.out.println("cat list size " + catList.size)


        } catch (e:Exception){

        }

    }
return catList

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecyclerviewnetworklistTheme {
        Greeting("Android")
    }
}