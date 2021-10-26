package com.user;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class serviciosMessage {

    @Autowired
    private MessageRepositorio metodoscrud;

    public List<Message> getAll() {
        return metodoscrud.getAll();
    }

    public Optional<Message> getMessage(int idMessage) {
        return metodoscrud.getMessage(idMessage);
    }

    public Message save(Message message) {
        if (message.getIdMessage() == null) {
            return metodoscrud.save(message);
        } else {
            Optional<Message> evt = metodoscrud.getMessage(message.getIdMessage());
            if (evt.isEmpty()) {
                return metodoscrud.save(message);
            } else {
                return message;
            }
        }
    }

    public Message update(Message message) {
        if (message.getIdMessage() != null) {
            Optional<Message> evt = metodoscrud.getMessage(message.getIdMessage());
            if (!evt.isEmpty()) {
                if (message.getMessageText() != null) {
                    evt.get().setMessageText(message.getMessageText());
                }
                metodoscrud.save(evt.get());
                return evt.get();
            } else {
                return message;
            }
        } else {
            return message;
        }
    }

    public boolean deleteMessage(int id) {
        Boolean del = getMessage(id).map(message -> {
            metodoscrud.delete(message);
            return true;
        }).orElse(false);
        return del;
    }
}
