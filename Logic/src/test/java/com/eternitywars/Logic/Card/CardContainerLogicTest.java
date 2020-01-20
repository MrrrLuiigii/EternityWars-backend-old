package com.eternitywars.Logic.Card;

import com.eternitywars.Models.CardCollection;
import com.eternitywars.Models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardContainerLogicTest {
    CardContainerLogic cardContainerLogic = new CardContainerLogic();

    @Test
    void getCardsByUserId() {
        User user = new User();
        user.setUserId(1);
        CardCollection cardCollection = cardContainerLogic.GetCardsByUserId(user, "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IlJVSkdSRUkzTWtOR01UWkVNRU5FTWtKRk1rUkdNelExT0RNMU4wTTBRVEF4UVRjeVJrRXpRZyJ9.eyJpc3MiOiJodHRwczovL2Rldi1ldGVybml0eXdhcnMuZXUuYXV0aDAuY29tLyIsInN1YiI6Imdvb2dsZS1vYXV0aDJ8MTExNzkzMDcyMjYzNTQyMTEwMjQ4IiwiYXVkIjpbImh0dHBzOi8vRXRlcm5pdHlXYXJzUmVzb3VyY2VTZXJ2ZXIiLCJodHRwczovL2Rldi1ldGVybml0eXdhcnMuZXUuYXV0aDAuY29tL3VzZXJpbmZvIl0sImlhdCI6MTU3OTU0NzQzNSwiZXhwIjoxNTc5NjMzODM1LCJhenAiOiI0NmdVZTJPeXltbng1ZkF2YjZ0UFNta3pCYjR5VE41RCIsInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwifQ.OgrMZQBmlYtTQ0d0Dbzk33WlEk6DGrL8kCV60wosz3xg_E200UY3N1givNkyexDnJzlsdUhGb_PMUJQAIV-2IqcJOCIkWfGhzxb8QChTxCVphjPEgvlo0w0HKdXGznEhtry-Qin7cn1o6DCTg_6u3ojxgPqQdtBl7wv-IXwUklsAwfDfpJTwpJ_FnM2QhJxTbc5ydu4ZE-j0IaAjKebCm7DrsWpcWubwTQVwZu3ZmIn49JxwWZadPyknrrrBzbN7ZO1ydCyCNrrAaV1ofqBQgtZD7smUI_bnreJnsEO4upwYmctwdBfezX_nZTwzJNArwh0vMBQSEbmIxLLAmHp2XA");
        assertEquals(cardCollection.getCards().size(), 32);
    }
}