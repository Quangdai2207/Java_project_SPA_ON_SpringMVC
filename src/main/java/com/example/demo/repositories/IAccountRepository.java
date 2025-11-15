package com.example.demo.repositories;

import com.example.demo.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IAccountRepository extends JpaRepository<Account, String> {
    // ** Get roles
    @Query("SELECT ra.role FROM RoleAccount ra WHERE ra.account.email = :email")
    public List<Role> getRoles(@Param("email") String email);

    // ** Get accounts by email
    @Query("FROM Account WHERE email = :email")
    Account findAccountByEmail(@Param("email") String email);

    // ** Get accounts by FirstName and LastName
    @Query("FROM Account WHERE firstName = :fname AND lastName = :lname")
    Account findAccountByFullName(@Param("fname") String fname, @Param("lname") String lname);

    // ** Get accounts by Phone
    @Query("SELECT p.account FROM Phone p WHERE p.phoneNumber = :phoneNumber")
    Account findAccountByPhone(@Param("phoneNumber") String phoneNumber);

    // ** Get accounts by Role
    @Query("SELECT r.account FROM RoleAccount r WHERE r.role.id = :roleID")
    List<Account> findAccountByRole(@Param("roleID") Integer roleID);

    // ** Get accounts by Status
    @Query("FROM Account WHERE status = :status")
    List<Account> findAllAccountByStatus(@Param("status") String status);

    // ** Get accounts by Role and Status
    @Query("SELECT r.account FROM RoleAccount r WHERE r.account.status = :status AND r.role.id = :roleID")
    List<Account> findAccountsByStatusAndRole(@Param("status") boolean status, @Param("roleID") Integer roleID);

    // ** Get accounts by address
    @Query("SELECT a.account FROM Address a WHERE a.province = :provice")
    Account findAccountByAddress(@Param("province") String province);

    //** Get list Address
    @Query("FROM Address a WHERE a.account.id = :accountId")
    List<Address> findAddress(@Param("accountId") String accountId);

    // ** Get account by faculty
    @Query("FROM Account WHERE faculty.id = :facultyID")
    List<Account> findAccountsByFaculty(@Param("facultyID") Integer facultyID);

    // ** Get account by Role and Faculty
    @Query("SELECT r.account FROM RoleAccount r WHERE r.role.id = :roleID AND r.account.faculty.id = :facultyID")
    List<Account> findAccountsByRoleAndFaculty(
            @Param("roleID") Integer roleID,
            @Param("facultyID") Integer facultyID
    );

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Account WHERE email = :email")
    void deleteByEmail(@Param("email") String email);

    @Query("FROM GeneralMedicalInfo gi WHERE gi.patient.id = :patientId")
    List<GeneralMedicalInfo> getGeneralMedicalInfo(@Param("patientId") String patientId);

    @Query("FROM MedicalRecord mc WHERE mc.patient.id = :patientId")
    List<MedicalRecord> getMedicalRecord(@Param("patientId") String patientId);

    @Query("FROM Phone p WHERE p.account.id = :accountId")
    List<Phone> getListPhoneByAccount(@Param("accountId") String accountId);
}
