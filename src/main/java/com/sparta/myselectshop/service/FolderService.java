package com.sparta.myselectshop.service;

import com.sparta.myselectshop.dto.FolderResponseDto;
import com.sparta.myselectshop.entity.Folder;
import com.sparta.myselectshop.entity.User;
import com.sparta.myselectshop.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;





    public void addFolders(List<String> folderNames, User user) {

        List<Folder> existFolderList = folderRepository.findAllByUserAndNameIn(user, folderNames);

        List<Folder> folderList = new ArrayList<>();

        for (String foldername : folderNames) {
            if(!isExistFolderName(foldername, existFolderList)){
                Folder folder = new Folder(foldername, user);
                folderList.add(folder);
            }else{
                throw new IllegalArgumentException("폴더명이 중복되었습니다.");
            }
            
        }
        folderRepository.saveAll(folderList);

    }

    public List<FolderResponseDto> getFolders(User user) {
        List<Folder> folderList = folderRepository.findAllByUser(user);
        List<FolderResponseDto> folderResponseDtoList = new ArrayList<>();
        for (Folder folder : folderList) {
            folderResponseDtoList.add(new FolderResponseDto(folder));
        }

        return folderResponseDtoList;
    }

    private boolean isExistFolderName(String foldername, List<Folder> existFolderList) {
        for (Folder existfolder : existFolderList) {
            if(foldername.equals(existfolder.getName())){
                return true;
            }
        }
        return false;
    }


}
