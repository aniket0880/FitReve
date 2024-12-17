import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uilover.project1932.Activity.ChatMessage
import com.uilover.project1932.R

class ChatAdapter(private val chatList: List<ChatMessage>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        // Inflate the correct layout based on the message sender
        val view = if (viewType == 1) {
            LayoutInflater.from(parent.context).inflate(R.layout.user_message_item, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.bot_message_item, parent, false)
        }
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val message = chatList[position]
        holder.messageTextView.text = message.message
    }

    override fun getItemCount() = chatList.size

    // Determine whether the message is from the user (1) or the bot (0)
    override fun getItemViewType(position: Int): Int {
        return if (chatList[position].isUser) 1 else 0
    }

    class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val messageTextView: TextView = view.findViewById(R.id.messageTextView)
    }
}