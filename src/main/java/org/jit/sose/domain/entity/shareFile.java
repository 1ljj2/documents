package org.jit.sose.domain.entity;

import java.util.Arrays;

/**
 * Date:2020-11-17
 * Time:15:38
 */
public class shareFile {
   private Integer fileId;
   private int[] roleIds;
   private int[] userIds;
   private String[] permissions;
   private Integer userId;
   private Integer roleId;
   private Integer deletePermission;
   private Integer updatePermission;
   private Integer downloadPermission;

    public shareFile(){

    }
    public shareFile(Integer fileId, Integer userId, Integer roleId, Integer deletePermission, Integer updatePermission, Integer downloadPermission) {
        this.fileId = fileId;
        this.userId = userId;
        this.roleId = roleId;
        this.deletePermission = deletePermission;
        this.updatePermission = updatePermission;
        this.downloadPermission = downloadPermission;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public int[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(int[] roleIds) {
        this.roleIds = roleIds;
    }

    public int[] getUserIds() {
        return userIds;
    }

    public void setUserIds(int[] userIds) {
        this.userIds = userIds;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getDeletePermission() {
        return deletePermission;
    }

    public void setDeletePermission(Integer deletePermission) {
        this.deletePermission = deletePermission;
    }

    public Integer getUpdatePermission() {
        return updatePermission;
    }

    public void setUpdatePermission(Integer updatePermission) {
        this.updatePermission = updatePermission;
    }

    public Integer getDownloadPermission() {
        return downloadPermission;
    }

    public void setDownloadPermission(Integer downloadPermission) {
        this.downloadPermission = downloadPermission;
    }

    @Override
    public String toString() {
        return "shareFile{" +
                "fileId=" + fileId +
                ", roleIds=" + Arrays.toString(roleIds) +
                ", userIds=" + Arrays.toString(userIds) +
                ", permissions=" + Arrays.toString(permissions) +
                ", userId=" + userId +
                ", roleId=" + roleId +
                ", deletePermission=" + deletePermission +
                ", updatePermission=" + updatePermission +
                ", downloadPermission=" + downloadPermission +
                '}';
    }
}
