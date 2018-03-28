package com.denwehrle.boilerplate.ui.contact

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.denwehrle.boilerplate.R
import com.denwehrle.boilerplate.data.local.model.Contact
import kotlinx.android.synthetic.main.item_contact.view.*
import javax.inject.Inject

/**
 * @author Dennis Wehrle
 */
class ContactAdapter @Inject constructor() : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    var onItemClick: ((Contact) -> Unit)? = null
    var contacts: List<Contact> = emptyList()

    override fun getItemCount(): Int {
        return contacts.size
    }

    /**
     * Create the ViewHolder with the layout specific for
     * one item inside the adapter.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false))
    }

    /**
     * Bind the data to the layout specific ui elements.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.firstNameText.text = contact.firstName.capitalize()
        holder.lastNameText.text = contact.lastName.capitalize()
        holder.email.text = contact.email

        Glide.with(holder.itemView.context)
                .load(contact.avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.avatarImage)
    }

    /**
     * We don't have any findViewById calls because we use the
     * kotlin android extensions to automatically inject the views.
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val firstNameText: TextView = itemView.textFirstName
        val lastNameText: TextView = itemView.textLastName
        val email: TextView = itemView.textEmail
        val avatarImage: ImageView = itemView.imageAvatar

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(contacts[adapterPosition])
            }
        }
    }
}