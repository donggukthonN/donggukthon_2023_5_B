package org.example.bliss_be.service;


import lombok.extern.slf4j.Slf4j;
import org.example.bliss_be.entity.Tree;
import org.example.bliss_be.repository.TreeRepository;
import org.example.bliss_be.dto.TreeDetailDTO;
import dto.TreeOverViewDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TreeService {
    private final TreeRepository treeRepository;
    public String getTreeDetail(TreeDetailDTO treeDetailDTO){
        String returnMsg = "";
        Optional<Tree> optionalTree = treeRepository.findById(treeDetailDTO.getTreeId());
        if (optionalTree.isPresent()) {
            treeDetailDTO.setOrnamentIdList(optionalTree.get());
            returnMsg = "트리 조회에 성공하였습니다.";
        } else {
            returnMsg = "존재하지 않는 트리입니다.";
        }

        return returnMsg;
    }
    public String getTreeOverView(TreeOverViewDTO treeOverViewDTO){
        String returnMsg = "";
        Optional<Tree> optionalTree = treeRepository.findById(treeOverViewDTO.getTreeId());
        if (optionalTree.isPresent()) {
            log.info("ornament size = {}", optionalTree.get().getOrnamentList());
            treeOverViewDTO.setOrnamentIdList(optionalTree.get());
            log.info("");
            returnMsg = "트리 조회에 성공하였습니다.";
        } else {
            returnMsg = "존재하지 않는 트리입니다.";
        }

        return returnMsg;
    }

    public Tree getTreeById(Long treeId) {
        Optional<Tree> optionalTree = treeRepository.findById(treeId);
        Tree tree = treeRepository.findById(treeId).orElseThrow(() ->{
            // err check
            throw new RuntimeException();
        });
        return tree;
    }
}
