package com.patika.userservice.service;

import com.patika.userservice.TestSupport;
import com.patika.userservice.entity.Address;
import com.patika.userservice.exception.ResourceNotFoundException;
import com.patika.userservice.repository.AddressRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest extends TestSupport {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    private Address addressObj;
    private Address updatedAddressObj;

    @BeforeEach
    void setup() {
        addressObj = generateAddress();
        updatedAddressObj = generateUpdatedAddress();
    }


    @Test
    void testCreateAddress_whenEverythingValid_shouldReturnAddress() {
        when(addressRepository.save(any(Address.class))).thenReturn(addressObj);

        Address addressResponse = addressService.save(addressObj);

        assertThat(addressResponse).isNotNull();
        assertThat(addressResponse.getAddressDescription()).isEqualTo(addressObj.getAddressDescription());
        assertThat(addressResponse.getAddressTitle()).isEqualTo(addressObj.getAddressTitle());
        assertThat(addressResponse.getProvince()).isEqualTo(addressObj.getProvince());

        verify(addressRepository, times(1)).save(any(Address.class));
        //verify(notificationProducer, times(1)).sendNotification(any(NotificationDTO.class));
    }

    @Test
    void testGetAllAddress_whenAddressesExist_shouldReturnListOfAddresses() {
        when(addressRepository.findAll()).thenReturn(Arrays.asList(addressObj, updatedAddressObj));

        List<Address> addressList = addressService.getAllAddresses();

        assertNotEquals(0, addressList.size());
        assertEquals(2, addressList.size());
        assertEquals("Izmir", addressList.get(0).getProvince());
        assertEquals("Istanbul", addressList.get(1).getProvince());
    }

    @Test
    void testGetAddressById_whenAddressExistsWithId_shouldReturnAddress() {

        //given
        when(addressRepository.findById(any(ObjectId.class))).thenReturn(Optional.of(addressObj));

        //when
        Address address = addressService.getAddressById(objectId);

        //then
        assertThat(address).isNotNull();
        assertThat(address.getProvince()).isEqualTo(addressObj.getProvince());
        assertThat(address.getAddressTitle()).isEqualTo(addressObj.getAddressTitle());
        assertThat(address.getAddressDescription()).isEqualTo(addressObj.getAddressTitle());

        verify(addressRepository, times(1)).findById(objectId);
    }

    @Test
    void testGetAddressById_whenAddressNotExist_shouldThrowException() {
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            addressService.getAddressById(objectId);
        });

        String expectedMessage = "Address not found";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testUpdateAddressDetails_whenAddressExist_shouldReturnAddress() {
        //given
        when(addressRepository.findById(any(ObjectId.class))).thenReturn(Optional.of(addressObj));
        when(addressRepository.save(any(Address.class))).thenReturn(updatedAddressObj);

        //when
        Address updatedAddress = addressService.updateAddressDetails(objectId, addressObj);

        //then
        assertThat(updatedAddress).isNotNull();
        assertThat(updatedAddress.getProvince()).isEqualTo(updatedAddressObj.getProvince());
        assertThat(updatedAddress.getAddressTitle()).isEqualTo(updatedAddressObj.getAddressTitle());
        assertThat(updatedAddress.getAddressDescription()).isEqualTo(updatedAddressObj.getAddressTitle());

        verify(addressRepository, times(1)).findById(objectId);
    }

    @Test
    void testDeleteAddress_whenAddressExist_shouldDeleteAddress() {
        //when
        when(addressRepository.findById(any(ObjectId.class))).thenReturn(Optional.ofNullable(addressObj));

        //then
        assertAll(() -> addressService.deleteAddress(objectId));
    }

}