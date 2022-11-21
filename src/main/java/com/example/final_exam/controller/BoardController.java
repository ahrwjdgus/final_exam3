package com.example.final_exam.controller;

import com.example.final_exam.entitiy.Board;
import com.example.final_exam.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    @GetMapping(value = "/write")
    public String boardWriteForm(Model model){
        return "board/boardWrite";
    }

    @PostMapping(value = "/boardsave")
    public String boardWriteDo(Board board, MultipartFile file) throws Exception{
        boardService.write(board,file);

        return "redirect:/board/list";
    }

    @GetMapping(value = "/list")
    public String boardList(Model model, @PageableDefault(page=0, size=10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Board> list = boardService.boardList(pageable);

        int nowPage = list.getPageable().getPageNumber()+1;
        int startPage = Math.max(nowPage - 4,1);
        int endPage = Math.min(nowPage + 5,list.getTotalPages());

        model.addAttribute("list",boardService.boardList(pageable));
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        return "board/boardlist";
    }

    @GetMapping(value = "/view") // /board/view?id=1
    public String boardView(Model model,Long id){
        model.addAttribute("board",boardService.boardView(id));
        return "board/boardview";
    }
    @GetMapping("/delete")
    public String boardDelete(Long id){
        boardService.boardDelete(id);

        return "redirect:/board/list";
    }

    @GetMapping("/modify/{id}")
    public String boardModify(@PathVariable("id") Long id, Model model){
        model.addAttribute("board",boardService.boardView(id));
        return "board/boardmodify";
    }

    @PostMapping("/update/{id}")
    public String boardUpdate(@PathVariable("id") Long id, Board board, MultipartFile file) throws Exception{
        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp,file);
        return "redirect:/board/list";
    }
}
