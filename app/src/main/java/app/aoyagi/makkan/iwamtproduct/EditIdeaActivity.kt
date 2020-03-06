package app.aoyagi.makkan.iwamtproduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_edit_idea.*

class EditIdeaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_idea)

        fab.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val idea = database.getReference("idea")

            val idea_list = hashMapOf(
                "title" to titleText.text.toString(),
                "content" to content.text.toString(),
                "pourpose" to pourpose.text.toString()

            )
            idea.setValue(idea_list)


        }
    }
}
