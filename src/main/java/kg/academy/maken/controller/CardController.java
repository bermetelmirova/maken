package kg.academy.maken.controller;

import kg.academy.maken.model.ResponseMessage;
import kg.academy.maken.model.card_model.*;
import kg.academy.maken.model.commment_model.CommentModel;
import kg.academy.maken.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseMessage<CardPostModel> save(@RequestBody CardPostModel cardModel) {
        return new ResponseMessage<CardPostModel>().prepareSuccessMessage(cardService.saveModel(cardModel));
    }

    @PostMapping("/add-comment")
    public ResponseMessage<CommentModel> addComment(@RequestBody CommentModel commentModel) {
        return new ResponseMessage<CommentModel>().prepareSuccessMessage(cardService.addComment(commentModel));
    }

    @PostMapping("/add-member")
    public ResponseMessage<CardMemberModel> addMember(@RequestBody CardMemberModel cardMemberModel) {
        return new ResponseMessage<CardMemberModel>().prepareSuccessMessage(cardService.addMember(cardMemberModel));
    }

    @GetMapping("/{id}")
    public ResponseMessage<CardPostModel> getById(@PathVariable Long id){
        return new ResponseMessage<CardPostModel>().prepareSuccessMessage(cardService.getModelById(id));
    }

    @GetMapping("/get-by-list/{id}")
    public List<CardModel> getCardByList(@PathVariable Long id) {
        return cardService.getCardsByList(id);
    }

    @GetMapping("/get-full-card/{id}")
    public ResponseMessage<CardGetModel> getCard(@PathVariable Long id) {
        return new ResponseMessage<CardGetModel>().prepareSuccessMessage(cardService.getCard(id));
    }

    @GetMapping("/get-all")
    public List<CardPostModel> getAll() {
        return cardService.getAllModel();
    }

    @GetMapping("/get-all/pageable")
    public Page<CardModel>  getPage(Pageable pageable){
        return  cardService.getPage(pageable);
    }

    @PutMapping("/move-card")
    public ResponseMessage<CardChangeListModel> moveCard(@RequestBody CardChangeListModel cardChangeListModel){
        return  new ResponseMessage<CardChangeListModel>()
                .prepareSuccessMessage(cardService.changeList(cardChangeListModel));
    }

    @PutMapping("/reject-task")
    public ResponseMessage<CardPostModel> rejectTask(@RequestBody CardPostModel cardPostModel){
        return new ResponseMessage<CardPostModel>().prepareSuccessMessage(cardService.rejectTask(cardPostModel));
    }

    @PutMapping("/accept-task")
    public ResponseMessage<CardRatingModel> acceptCard(@RequestBody CardRatingModel cardRatingModel){
        return  new ResponseMessage<CardRatingModel>().prepareSuccessMessage(cardService.acceptTask(cardRatingModel));
    }

    @PutMapping("/update-card")
    public ResponseMessage<CardPostModel> update(@RequestBody CardPostModel cardPostModel){
        return new ResponseMessage<CardPostModel>().prepareSuccessMessage(cardService.update(cardPostModel));
    }

    @DeleteMapping("/{id}")
    public  ResponseMessage<CardPostModel> deleteById(@PathVariable Long id){
        return new ResponseMessage<CardPostModel>().prepareSuccessMessage(cardService.deleteModelById(id));
    }

    @DeleteMapping("/remove-member-card")
    public ResponseMessage<CardMemberModel> removeMember(@RequestBody CardMemberModel cardMemberModel){
        return new ResponseMessage<CardMemberModel>().prepareSuccessMessage(cardService.removeMember(cardMemberModel));
    }
}
