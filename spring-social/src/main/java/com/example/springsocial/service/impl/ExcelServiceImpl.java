package com.example.springsocial.service.impl;

import com.example.springsocial.model.documents.Documents;
import com.example.springsocial.model.quiz.Quiz;
import com.example.springsocial.model.quiz_answer.QuizAnswer;
import com.example.springsocial.repository.DocumentRepository;
import com.example.springsocial.service.DocumentService;
import com.example.springsocial.service.ExcelService;
import com.example.springsocial.service.QuizAnswerService;
import com.example.springsocial.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {
    private final QuizService quizService;
    private final QuizAnswerService quizAnswerService;
    private final DocumentRepository documentRepository;

    @Override
    public boolean compareMergeCell(InputStream inputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);
        Cell cellB1 = row.getCell(1);
        Cell cellF1 = row.getCell(6);
        return areMergedCellsInSameRowEqual(cellB1,cellF1,sheet);
    }

    @Override
    public void excelToQuizzes(InputStream inputStream, Documents documents) throws IOException {
        try {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            //Thiết lập phạm vi cột và dòng đẩy dữ liệu vào quiz
            importDataToQuiz(sheet,documents);
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse Excel file: " + e.getMessage());
        }
    }

    private void importDataToQuiz(Sheet sheet, Documents documents) {
        Iterator<Row> rows = sheet.iterator();
        List<Quiz> quizzes = new ArrayList<>();
        int rowNumber = 1;
        while (rows.hasNext()){
            Row row = rows.next();
            if (rowNumber <= 2){
                rowNumber++;
                continue;
            }
            Quiz quiz = new Quiz();
            quiz.setQuestion(row.getCell(0).getStringCellValue());

            List<QuizAnswer> quizAnswers = new ArrayList<>();
            for (int i = 1;i<5;i++){
                if (row.getCell(i) != null) {
                    QuizAnswer quizAnswer = new QuizAnswer();;
                    quizAnswer.setAnswer(row.getCell(i).getStringCellValue());
                    quizAnswer.setIsCorrected(row.getCell(i+5).getBooleanCellValue());
                    quizAnswers.add(quizAnswer);
                }
                quiz.setQuizAnswers(quizAnswers);
                quizzes.add(quiz);
            }
        }
//        quizService.saveAll(quizzes);
        documents.setQuizzes(quizzes);
        documentRepository.save(documents);

    }

    private boolean areMergedCellsInSameRowEqual(Cell cellB1, Cell cellF1, Sheet sheet) {
        CellRangeAddress mergedRegionB1 = getMergedRegion(cellB1, sheet);
        CellRangeAddress mergedRegionF1 = getMergedRegion(cellF1,sheet);
        assert mergedRegionB1 != null;
        assert mergedRegionF1 != null;
        return mergedRegionB1.getNumberOfCells() == mergedRegionF1.getNumberOfCells();
    }

    private CellRangeAddress getMergedRegion(Cell cell, Sheet sheet) {
        for (CellRangeAddress mergedRegion : sheet.getMergedRegions()) {
            if (mergedRegion.isInRange(cell.getRowIndex(), cell.getColumnIndex())) {
                return mergedRegion;
            }
        }
        return null;
    }

}
