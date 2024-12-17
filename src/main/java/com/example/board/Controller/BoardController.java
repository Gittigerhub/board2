package com.example.board.Controller;

import com.example.board.DTO.BoardDTO;
import com.example.board.Service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller             // get, post 매핑만 사용이 가능
// @RestController            // create, modify, remove, get, put
@RequiredArgsConstructor
@Log                       // System.out.println을 대체해서 사용
public class BoardController {

    private final BoardService boardService;

    // localhost로 접속시 연결할 페이지(get)
    @GetMapping("/")
    public String index() {
        log.info("시작페이지 이동...");

        return "redirect:/list";  // 페이지 대신 맵핑으로 연결
    }

    // return 중에서 중복되는 부분을 찾아서 맵핑이름과 동일한 부분이 기준
    // 전체 목록 페이지(기준)
    @GetMapping("/list")
    public String listForm(Model model) {
        log.info("모든 데이터를 읽어온다...");
        List<BoardDTO> boardDTOList = boardService.list();

        model.addAttribute("boardDTOList", boardDTOList);
        return "list";
    }

    // 추가버튼을 클릭했을 때 입력폼 페이지
    @GetMapping("/insert")
    public String insertForm() {
        log.info("입력폼 페이지 이동...");

        return "insert";
    }

    // 입력폼에서 저장버튼을 클릭했을 때 처리
    @PostMapping("/insert")
    // ModelAttribute : HTML의 내용을 DTO에 저장
    public String insertProc(@ModelAttribute BoardDTO boardDTO) {
        log.info("입력폼 내용을 저장...");
        boardService.insert(boardDTO);

        return "redirect:list";
    }

    // 목록에서 상세버튼을 클릭했을 때 상세보기 처리 후, 데이터를 페이지 전달
    // Form으로 전달시에는 get, post로 전달가능
    // 그 외에는 get로만 전달가능
    @GetMapping("/read")
    public String readProc(@RequestParam Integer id, Model model) {
        log.info("개별읽기...");
        BoardDTO boardDTO =  boardService.read(id);     // 전달자가 있으면 변수로 받는다.(리턴이 있으면)

        log.info("개별정보를 페이지에 전달...");
        model.addAttribute("board", boardDTO);
        return "read";
    }

    // 목록에서 수정버튼을 클릭했을 때 수정폼 페이지
    @GetMapping("/update")
    public String updateForm(@RequestParam Integer id, Model model) {
        log.info("수정할 데이터 읽기...");
        BoardDTO boardDTO =  boardService.read(id);

        log.info("개별정보를 페이지에 전달...");
        model.addAttribute("board", boardDTO);
        return "update";
    }

    // 수정폼에서 수정버튼을 클릭했을 때 수정 처리
    @PostMapping("/update")
    public String updateProc(@ModelAttribute BoardDTO boardDTO) {
        log.info("수정된 데이터 저장...");
        boardService.update(boardDTO);

        return "redirect:list";
    }

    // 목록에서 삭제버튼을 클릭했을 때 삭제 처리
    // @GetMapping("/delete/{id}") = rest 방식
    @PostMapping("/delete")
    public String deleteProc(Integer id) {
        log.info("삭제 처리...");
        boardService.delete(id);

        return "redirect:list";
    }

}
