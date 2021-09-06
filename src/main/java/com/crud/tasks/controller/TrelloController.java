package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/trello")
@RequiredArgsConstructor
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {

        return trelloClient.getTrelloBoards();


  //      Optional<List<TrelloBoardDto>> boardsOptional = Optional.ofNullable(trelloBoards);

//        boardsOptional.ifPresent(list -> list.stream()
//                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla"))
//                .filter(trelloBoardDto -> trelloBoardDto.getName() != null)
//                .filter(trelloBoardDto -> trelloBoardDto.getId() != null)
//                .forEach(trelloBoardDto -> {
//                    System.out.println(trelloBoardDto.getId() + " - " + trelloBoardDto.getName());
//                    System.out.println("This board contains lists: ");
//                    trelloBoardDto.getLists().forEach(trelloList -> {
//                        System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed());
//                    });
//                }));
    }

    @PostMapping("createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }
}
