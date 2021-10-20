package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    void testMapToBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        TrelloListDto trelloList = new TrelloListDto("trello dto id", "trello dto list", false);
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);
        trelloBoardsDto.add(new TrelloBoardDto("id","test", trelloLists));
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);
        //Then
        assertEquals(1, trelloBoards.size());
        assertEquals("test", trelloBoards.get(0).getName());
        assertEquals("trello dto id", trelloBoards.get(0).getLists().get(0).getId());
    }

    @Test
    void testMapToBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList trelloList = new TrelloList("trello id", "trello list", true);
        trelloLists.add(trelloList);
        trelloBoards.add(new TrelloBoard("id", "name 2", trelloLists));
        //When
        List<TrelloBoardDto> testedDtoBoards = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(1, testedDtoBoards.size());
        assertEquals("name 2", testedDtoBoards.get(0).getName());
        assertEquals("trello id", testedDtoBoards.get(0).getLists().get(0).getId());
    }

    @Test
    void testMapToList() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        TrelloListDto trelloListDto1 = new TrelloListDto("dto list1 id", "dto list1 name", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("dto list2 id", "dto list2 name", false);
        trelloListDtos.add(trelloListDto1);
        trelloListDtos.add(trelloListDto2);
        //When
        List<TrelloList> testedList = trelloMapper.mapToList(trelloListDtos);
        //Then
        assertEquals(2, testedList.size());
        assertFalse(testedList.get(1).isClosed());
    }

    @Test
    void testMapToListDto() {
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        TrelloList trelloList1 = new TrelloList("list1 id", "list1 name", true);
        TrelloList trelloList2 = new TrelloList("list2 id", "list2 name", false);
        trelloList.add(trelloList1);
        trelloList.add(trelloList2);
        //When
        List<TrelloListDto> testedList = trelloMapper.mapToListDto(trelloList);
        //Then
        assertEquals(2, testedList.size());
        assertTrue(testedList.get(0).isClosed());
    }

    @Test
    void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name", "description", "pos", "listId");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("name", trelloCardDto.getName());
        assertEquals("description", trelloCardDto.getDescription());
        assertEquals("pos", trelloCardDto.getPos());
        assertEquals("listId", trelloCardDto.getListId());
    }

    @Test
    void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("n", "desc", "bottom", "id");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("n", trelloCard.getName());
        assertEquals("desc", trelloCard.getDescription());
        assertEquals("bottom", trelloCard.getPos());
        assertEquals("id", trelloCard.getListId());
    }
}