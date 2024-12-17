package com.example.board.Service;

import com.example.board.DTO.BoardDTO;
import com.example.board.Entity.BoardEntity;
import com.example.board.Repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional      // 데이터베이스에 작업할 레코드들을 모아서 한번에 처리
@RequiredArgsConstructor    // 자동 주입, 클래스 생성자를 통해 생성 후 사용, 에전에 사용했던 Autowired 대체해 사용
public class BoardService {

    /*
    옛날 사용법
    @Autowired
    private BoardRepository boardRepository;
    */

    private final BoardRepository boardRepository;          // 우리가 사용할 SQL문
    private final ModelMapper modelMapper;                  // AppConfig에서 선언해둬서 new생성자로 만들필요 없음

    // Controller과 Service는 DTO로 전달
    public void insert(BoardDTO boardDTO) {         // 삽입, 입력폼에서 입력받은 내용을 데이터베이스 저장
        // 가져온 DTO를 Entity로 반드시 변환해야 한다.
        // map은 변수, 값으로 구성된 배열
        // BoardDTO변수들을 BoardEntity변수에 맞게 변환
        BoardEntity boardEntity = modelMapper.map(boardDTO, BoardEntity.class);

        boardRepository.save(boardEntity);
    }

    public void update(BoardDTO boardDTO) {         // 수정, 수정폼에서 수정한 내용을 데이터베이스 저장
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(boardDTO.getId());
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = modelMapper.map(boardDTO, BoardEntity.class);

            boardRepository.save(boardEntity);
        }
    }

    public void delete(Integer id) {                // 삭제, 게시글번호로 해당 자료를 데이터베이스에서 삭제
        boardRepository.deleteById(id);
    }

    public List<BoardDTO> list() {                        // 전체목록, 데이터베이스에서 모든 데이터를 화면에 출력
        // 필드:필드:필드 - 레코드(map)
        // 레코드:레코드:레코드(List)
        List<BoardEntity> boardEntityList = boardRepository.findAll();      // 모두 조회
        // Repository <-> Service   Entity <-> DTO   Controller
        // 여러개의 레코드를 하나씩 DTO로 변환해서 다시 배열에 저장 후 List 변환
        List<BoardDTO> boardDTOList = Arrays.asList(modelMapper.map(boardEntityList, BoardDTO[].class));
        return boardDTOList;
    }

    public BoardDTO read(Integer id) {              // 상세보기, 개별정보, 게시글번호의 데이터를 화면에 출력
        return null;
    }

}