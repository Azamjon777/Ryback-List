package com.example.ryback


import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_content.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var adapter: MyAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav_view.setNavigationItemSelectedListener(this)

        var list = ArrayList<ListItem>()


        list.addAll(fillArrays(resources.getStringArray(R.array.fish),
            resources.getStringArray(R.array.fish_content),
        getImageId(R.array.fish_image_array)))

        rcView.hasFixedSize()
        rcView.layoutManager = LinearLayoutManager(this)
        adapter= MyAdapter(list, this)
        rcView.adapter = adapter



    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){

            R.id.id_fish -> {
                Toast.makeText(this, "рыба", Toast.LENGTH_SHORT).show()
                adapter?.notifyDataSetChanged()
            }
            R.id.id_najivka -> Toast.makeText(this, "наживка", Toast.LENGTH_SHORT).show()
            R.id.id_snasty -> Toast.makeText(this, "снасти", Toast.LENGTH_SHORT).show()
            R.id.id_history -> Toast.makeText(this, "история", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    fun fillArrays(titlArray: Array<String>, contentArray:Array<String>, imageArray: IntArray):List<ListItem>{
        var listItemArray = ArrayList<ListItem>()
        for (n in 0..titlArray.size-1){
            var listitem = ListItem(imageArray[n], titlArray[n], contentArray[n])
            listItemArray.add(listitem)
        }
        return listItemArray
    }

    fun getImageId(imageArrayId:Int): IntArray{
        var tArray:TypedArray = resources.obtainTypedArray(imageArrayId)
        val count = tArray.length()
        val Ids = IntArray(count)
        for (i in Ids.indices){
            Ids[i] = tArray.getResourceId(i, 0)
        }
        tArray.recycle()
        return Ids
    }

}