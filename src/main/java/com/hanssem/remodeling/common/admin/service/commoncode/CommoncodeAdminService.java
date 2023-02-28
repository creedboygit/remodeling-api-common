package com.hanssem.remodeling.common.admin.service.commoncode;

import com.hanssem.remodeling.common.admin.repository.commoncode.CommoncodeAdminRepository;
import com.hanssem.remodeling.common.admin.service.ExcelImportAbstract;
import com.hanssem.remodeling.common.admin.service.commoncode.dto.AddCodeDto;
import com.hanssem.remodeling.common.admin.service.commoncode.mapper.CommoncodeAdminMapper;
import com.hanssem.remodeling.common.common.error.CustomException;
import com.hanssem.remodeling.common.constant.ResponseCode;
import com.hanssem.remodeling.common.domain.commoncode.entity.Commoncode;
import com.hanssem.remodeling.common.domain.commoncode.entity.CommoncodeValue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommoncodeAdminService extends ExcelImportAbstract {

    private final CommoncodeAdminRepository commoncodeAdminRepository;


    public void addCodeImport(MultipartFile file, String extension) throws IOException {

        List<AddCodeDto> codeDtos = readCodeDto(file, extension);
        List<AddCodeValueDto> codeValueDtos = readCodeValueDto(file, extension);

        saveCommoncode(codeDtos, codeValueDtos);
    }

    @Transactional
    public void saveCommoncode(List<AddCodeDto> codeDtos, List<AddCodeValueDto> codeValueDtos) {
        for (AddCodeDto codeDto : codeDtos) {
            Commoncode findCommoncode = commoncodeAdminRepository.findCommoncode(codeDto.getCodeName());
            if (findCommoncode == null) {
                newData(codeValueDtos, codeDto);
            } else {
                commoncodeAdminRepository.delete(findCommoncode);
                newData(codeValueDtos, codeDto);
            }
        }

    }

    private void newData(List<AddCodeValueDto> codeValueDtos, AddCodeDto codeDto) {
        String upperCodeId = "";
        if (codeDto.getUpperCodeName() != null && codeDto.getUpperCodeName().length() > 0) {
            upperCodeId = commoncodeAdminRepository.findByCodeName(codeDto.getUpperCodeName());
        }

        Commoncode commoncode = Commoncode.builder()
            .codeId(getUUID())
            .standardClassId(codeDto.getStandardClassId())
            .codeTypeCode(codeDto.getCodeTypeCode())
            .codeName(codeDto.getCodeName())
            .codeKorName(codeDto.getCodeKorName())
            .codeDescription(codeDto.getCodeDescription())
            .validEndDatetime(codeDto.getValidEndDatetime())
            .upperCodeId(upperCodeId)
            .build();

        List<AddCodeValueDto> collect = codeValueDtos.stream()
            .filter(x -> x.getCodeName().equals(commoncode.getCodeName()))
            .collect(Collectors.toList());
        List<CommoncodeValue> codeValues = CommoncodeAdminMapper.INSTANCE.toCommoncodeValueEntity(collect);
        for (CommoncodeValue codeValue : codeValues) {
            codeValue.setCodeValueId(getUUID());
        }
        commoncode.setChildren(codeValues);

        commoncode.association();
        commoncodeAdminRepository.save(commoncode);
    }

    private String getUUID() {
        return UUID.randomUUID().toString().toUpperCase().replace("-", "");
    }

    private List<AddCodeDto> readCodeDto(MultipartFile file, String extension) throws IOException {
        List<AddCodeDto> dataList = new ArrayList<>();
        Workbook workbook = null;

        if (extension.equals("xlsx") || extension.equals("tsv")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = null;
        try {
            worksheet = workbook.getSheetAt(0);
        } catch (NullPointerException e) {
            throw new CustomException(ResponseCode.NOT_FOUND_EXCEL_SHEET);
        }

        for (int i = 2; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4
            Row row = worksheet.getRow(i);
            AddCodeDto data = new AddCodeDto();

            data.setStandardClassId(readString(row, 0));
            data.setCodeTypeCode(Integer.toString(readInteger(row, 1)));
            data.setCodeName(readString(row, 2));
            data.setCodeKorName(readString(row, 3));
            data.setCodeDescription(readString(row, 4));
            data.setValidEndDatetime(row.getCell(5).getLocalDateTimeCellValue());
            data.setUpperCodeName(readString(row, 6));

            dataList.add(data);
        }

        return dataList;
    }


    private List<AddCodeValueDto> readCodeValueDto(MultipartFile file, String extension) throws IOException {
        List<AddCodeValueDto> dataList = new ArrayList<>();
        Workbook workbook = null;

        if (extension.equals("xlsx") || extension.equals("tsv")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = null;
        try {
            worksheet = workbook.getSheetAt(1);
        } catch (NullPointerException e) {
            throw new CustomException(ResponseCode.NOT_FOUND_EXCEL_SHEET);
        }

        for (int i = 2; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4
            Row row = worksheet.getRow(i);
            AddCodeValueDto data = new AddCodeValueDto();

            data.setCodeName(readString(row, 0));
            data.setCodeValue(readString(row, 1));
            data.setCodeValueName(readString(row, 2));
            data.setDescription(readString(row, 3));
            data.setCodeValueSeq(readInteger(row, 4));
            data.setValidEndDatetime(row.getCell(5).getLocalDateTimeCellValue());
            data.setEtcValue1(readString(row, 6));
            data.setEtcValue2(readString(row, 7));

            dataList.add(data);
        }

        return dataList;
    }

}
