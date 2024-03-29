package net.ticherhaz.notanotanota

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import net.ticherhaz.notanotanota.databinding.ActivityAaaBinding

class AAA : AppCompatActivity() {
    private lateinit var binding: ActivityAaaBinding
    private var lll: MutableList<YYY> = mutableListOf()
    private var ccc: CustomAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAaaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (ZZZ.getInstance()!!.isKeyExists(COO.NOTE_LIST)) lll =
            ZZZ.getInstance()!!.getObjectsListCustom<YYY>(COO.NOTE_LIST)!!

        //Creating the adapter
        ccc = CustomAdapter(lll as ArrayList<YYY>)
        binding.rv.apply {
            adapter = ccc
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_about) {
            dd()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun dd() {
        val dd = Dialog(this@AAA)
        dd.setContentView(R.layout.hihi)
        dd.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dd.show()
    }

    fun fff(view: View) {
        val tt = binding.et.text.toString().trim()
        val ttt = System.currentTimeMillis()
        val xxx = YYY(ttt.toString(), tt, ttt)
        lll.add(xxx)
        ZZZ.getInstance()!!.saveObjectsList(COO.NOTE_LIST, lll)
        ccc?.notifyDataSetChanged()
        binding.rv.scrollToPosition(lll.size - 1)
        binding.et.text.clear()
    }

    class CustomAdapter(private val dd: MutableList<YYY>) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.ii, parent, false)
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = dd[position].d
        }

        override fun getItemCount(): Int {
            return dd.size
        }

        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val textView: TextView = v.findViewById(R.id.tv)
        }
    }
}