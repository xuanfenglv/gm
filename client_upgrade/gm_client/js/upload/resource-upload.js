function upload() {
	var formData = getAllFiles();
	if(isEmpty(formData)) {
		tip("无选中的文件");
		return;
	}
	post(server.resource,formData,()=>{
		success("文件上传成功")
		clearAll();
	},true);
}dragFiles