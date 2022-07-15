package ru.cft.shift.scheduler.ui.calendar.event.modal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.cft.shift.scheduler.R
import ru.cft.shift.scheduler.databinding.FragmentEventBinding
import ru.cft.shift.scheduler.ui.base.BaseFragment
import ru.cft.shift.scheduler.ui.calendar.event.EventMvpPresenter
import ru.cft.shift.scheduler.ui.event.EventActivity
import javax.inject.Inject

class EventModalFragment(
    private val eventPresenter: EventMvpPresenter
) : BaseFragment<EventModalMvpPresenter>(), EventModalMvpView {

    private companion object {
        const val MIME_PLAIN_TEXT = "plain/text"
    }

    private lateinit var binding: FragmentEventBinding

    @Inject
    override lateinit var presenter: EventModalMvpPresenter

    override val eventId: Long?
        get() = eventPresenter.eventId

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventBinding.inflate(layoutInflater)

        binding.background.setOnClickListener { hideModalWindow() }
        binding.window.setOnClickListener(null)
        binding.share.setOnClickListener { presenter.onShareClick() }
        binding.edit.setOnClickListener { presenter.onEditEventClick() }
        binding.delete.setOnClickListener { presenter.onDeleteEventClick() }

        return binding.root
    }

    override fun hideModalWindow() {
        val fragment = parentFragmentManager.findFragmentById(R.id.modal_window)
        fragment?.let {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.remove(it)
            transaction.commit()
        }
    }

    override fun removeEvent() {
        eventPresenter.remove();
    }

    override fun showShareWindow(url: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.type = MIME_PLAIN_TEXT
        intent.putExtra(Intent.EXTRA_TEXT, url)
        startActivity(Intent.createChooser(intent, resources.getString(R.string.delete)))
    }

    override fun showEventMenu() {
        val intent = Intent(context, EventActivity::class.java)
        // TODO передавать данные события (айдишника хватит)
        startActivity(intent)
    }
}