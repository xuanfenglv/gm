function upload() {
	var formData = getAllFiles();
	if(isEmpty(formData)) {
		tip("无选中的文件");
		return;
	}
	
	post(server.img,formData,()=>{
		success("文件上传成功")
		clearAll();
	},true);
}