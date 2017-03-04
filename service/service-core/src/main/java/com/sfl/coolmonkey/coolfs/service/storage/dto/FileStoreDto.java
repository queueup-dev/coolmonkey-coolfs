package com.sfl.coolmonkey.coolfs.service.storage.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.InputStream;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/17/16
 * Time: 6:59 PM
 */
public class FileStoreDto {

    //region Properties
    private InputStream inputStream;

    private String fileName;

    private String contentType;

    private FileMetaDataDto fileMetaDataDto;
    //endregion

    //region Constructors
    public FileStoreDto() {
    }

    public FileStoreDto(final InputStream inputStream,
                        final String fileName,
                        final String contentType,
                        final FileMetaDataDto fileMetaDataDto) {
        this.inputStream = inputStream;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileMetaDataDto = fileMetaDataDto;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileStoreDto)) {
            return false;
        }
        final FileStoreDto that = (FileStoreDto) o;
        return new EqualsBuilder()
                .append(inputStream, that.inputStream)
                .append(fileName, that.fileName)
                .append(contentType, that.contentType)
                .append(fileMetaDataDto, that.fileMetaDataDto)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(inputStream)
                .append(fileName)
                .append(contentType)
                .append(fileMetaDataDto)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("inputStream", inputStream)
                .append("fileName", fileName)
                .append("contentType", contentType)
                .append("fileMetaDataDto", fileMetaDataDto)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(final InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }

    public FileMetaDataDto getFileMetaDataDto() {
        return fileMetaDataDto;
    }

    public void setFileMetaDataDto(final FileMetaDataDto fileMetaDataDto) {
        this.fileMetaDataDto = fileMetaDataDto;
    }
    //endregion
}
