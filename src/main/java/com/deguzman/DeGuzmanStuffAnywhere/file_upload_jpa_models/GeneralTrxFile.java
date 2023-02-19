package com.deguzman.DeGuzmanStuffAnywhere.file_upload_jpa_models;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.bind.annotation.CrossOrigin;

@Entity
@Table(name = "general_trx_files")
@CrossOrigin
public class GeneralTrxFile {

	private String fileId;
	private String filename;
	private String type;
	
	@Lob
	private byte[] data;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	@Column(name = "filename")
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(data);
		result = prime * result + ((fileId == null) ? 0 : fileId.hashCode());
		result = prime * result + ((filename == null) ? 0 : filename.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeneralTrxFile other = (GeneralTrxFile) obj;
		if (!Arrays.equals(data, other.data))
			return false;
		if (fileId == null) {
			if (other.fileId != null)
				return false;
		} else if (!fileId.equals(other.fileId))
			return false;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "GeneralTrxFile [fileId=" + fileId + ", filename=" + filename + ", type=" + type + ", data="
				+ Arrays.toString(data) + "]";
	}
	public GeneralTrxFile(String fileId, String filename, String type, byte[] data) {
		super();
		this.fileId = fileId;
		this.filename = filename;
		this.type = type;
		this.data = data;
	}
	public GeneralTrxFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
