package com.airtel.search.service;

import java.util.List;

public interface IFindCommonWordsService {
    
    List<String> findCommonWords(List<String> filePaths);

}
