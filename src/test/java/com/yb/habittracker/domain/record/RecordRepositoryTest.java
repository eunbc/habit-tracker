package com.yb.habittracker.domain.record;

import static org.assertj.core.api.Assertions.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.github.f4b6a3.ulid.UlidCreator;
import com.yb.habittracker.domain.habit.HabitEntity;
import com.yb.habittracker.domain.habit.HabitRepository;
import com.yb.habittracker.domain.record.RecordEntity;
import com.yb.habittracker.domain.record.RecordRepository;

@DataJpaTest
// @SpringBootTest
class RecordRepositoryTest {


	@Autowired
	HabitRepository habitRepository;
	@Autowired
	RecordRepository recordRepository;
	@Autowired
	EntityManager entityManager;
	
	RecordEntity record = RecordEntity.builder().recordId(UlidCreator.getUlid().toString()).recordDate(ZonedDateTime.now()).build();

	@Test
	public void saveRecord() {
		//when
		RecordEntity entity = recordRepository.save(record);

		//then
		assertThat(entity).isNotNull();
		assertThat(entity.getRecordId()).isEqualTo(record.getRecordId());
	}

	@Test
	public void findAll() {
		//given
		recordRepository.save(record);

		//when
		List<RecordEntity> result = recordRepository.findAll();

		//then
		assertThat(result.size()).isEqualTo(1);
		assertThat(result).hasSize(1).extracting(RecordEntity::getRecordId).containsExactly(record.getRecordId());
	}

	@Test
	@Transactional
	public void findAllByHabit() {
		//given
		HabitEntity habitSave = HabitEntity.builder().habitId("test_id").name("test_name").build();
		entityManager.persist(habitSave);

		RecordEntity record1 = RecordEntity.builder().recordId(UlidCreator.getUlid().toString()).recordDate(ZonedDateTime.now()).build();
		record1.setHabit(habitSave);
		recordRepository.save(record1);

		RecordEntity record2 = RecordEntity.builder().recordId(UlidCreator.getUlid().toString()).recordDate(ZonedDateTime.now()).build();
		record2.setHabit(habitSave);
		recordRepository.save(record2);

		//when
		List<RecordEntity> recordEntityList = recordRepository.findAllByHabit(habitSave);

		//then
		assertThat(recordEntityList).hasSize(2);
		assertThat(recordEntityList.get(0).getRecordId()).isEqualTo(record1.getRecordId());
		assertThat(recordEntityList.get(1).getRecordId()).isEqualTo(record2.getRecordId());
	}

	@Test
	public void deleteHabit() {
		//given
		RecordEntity entity = recordRepository.save(record);

		//when
		recordRepository.deleteById(entity.getRecordId());

		//then
		Optional<RecordEntity> find = recordRepository.findById(record.getRecordId());
		assertThat(find.isEmpty()).isTrue();
	}


}